package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.UserDTO;
import Oracle.Partner.Tracker.entities.User;
import Oracle.Partner.Tracker.repositories.UserRepository;
import Oracle.Partner.Tracker.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends CsvService<UserDTO>{

    @Autowired
    UserRepository userRepository;


    public List<User> findAllUsers() {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return allUsers;
    }

    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    public User registerNewUser(UserDTO user) {
        if (userRepository.existsByEmail(user.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        User newUser = new User();
        newUser.setStatus(Status.ACTIVE);
        newUser.setIngestionOperation(IngestionOperation.MANUAL);
        BeanUtils.copyProperties(user, newUser);
        return userRepository.save(newUser);
    }

    public User updateUser(Long id, UserDTO userDTO){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User userUpdate = user.get();
        userUpdate.setUpdateAt(LocalDateTime.now());

        BeanUtils.copyProperties(userDTO, userUpdate);
        return userRepository.save(userUpdate);
    }

    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> mapCsvToEntities(List<String[]> csvData){
        List<UserDTO> users = new ArrayList<>();

        String[] header = csvData.get(0);

        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);
            Optional<UserDTO> userDTO = mapRowToUser(header, row);
            if (userDTO.isPresent()) {
                users.add(userDTO.get());
            }
        }

        return users;
    }

    private Optional<UserDTO> mapRowToUser(String[] header, String[] row){
        UserBuilder userBuilder = new UserBuilder();
        for (int i = 0; i < header.length; i++){
            if (i >= row.length) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Row has fewer elements than header");
            }
            String headerValue = header[i];
            String rowValue = row[i];
            if ("email".equals(headerValue) && userRepository.existsByEmail(rowValue)){
                // Se o email já existir, retorne Optional.empty()
                return Optional.empty();
            }
            if (headerValue.equals("OPN Admin Name") || headerValue.equals("OPN Admin Email") || headerValue.equals("password") || headerValue.equals("role") || headerValue.equals("Membership Type")){
                switch (headerValue){
                    case "OPN Admin Name" -> userBuilder.setName(rowValue);
                    case "OPN Admin Email" -> userBuilder.setEmail(rowValue);
                    case "password" -> userBuilder.setPassword(rowValue);
                    case "role" -> userBuilder.setRole(RoleEnum.toRole(rowValue));
                    case "Membership Type" -> userBuilder.setMemberShipType(MembershipEnum.toMembership(rowValue));
                    default -> {}
                }
            }

        }
        UserDTO userDTO = new UserDTO(userBuilder.getName(), userBuilder.getEmail(), userBuilder.getPassword(), userBuilder.getRole(), userBuilder.getStatus(), userBuilder.getIngestionOperation(), userBuilder.getMemberShipType());
        User user = new User();
        BeanUtils.copyProperties(userBuilder, userDTO);
        BeanUtils.copyProperties(userDTO, user);
        user.setIngestionOperation(IngestionOperation.CSV);
        user.setStatus(Status.ACTIVE);

        userRepository.save(user);

        return Optional.of(userDTO);
    }
}
