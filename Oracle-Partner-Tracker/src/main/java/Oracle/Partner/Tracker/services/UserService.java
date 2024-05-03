package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.dto.UserDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.entities.User;
import Oracle.Partner.Tracker.repositories.CompanyRepository;
import Oracle.Partner.Tracker.repositories.UserRepository;
import Oracle.Partner.Tracker.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Service
public class UserService extends CsvService<UserDTO>{

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyRepository companyRepository;


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
        String[] header = csvData.get(0);
        return csvData.stream().skip(1)
                .map(row -> mapRowToUser(header, row))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<UserDTO> mapRowToUser(String[] header, String[] row){
        UserBuilder userBuilder = new UserBuilder();
        Company company = new Company();
        Map<String, BiConsumer<String, UserBuilder>> fieldSetterMap = new HashMap<>();
        fieldSetterMap.put("OPN Admin Name", (value, builder) -> builder.setName(value));
        fieldSetterMap.put("OPN Admin Email", (value, builder) -> builder.setEmail(value));
        fieldSetterMap.put("Membership Type", (value, builder) -> builder.setMemberShipType(MembershipEnum.toMembership(value)));

        UserDTO userDTO = new UserDTO(userBuilder.getName(), userBuilder.getCompany(),userBuilder.getEmail(), userBuilder.getPassword(),userBuilder.getRole(),userBuilder.getStatus(), userBuilder.getIngestionOperation(), userBuilder.getMemberShipType());
        User user = new User();
        BeanUtils.copyProperties(userBuilder, userDTO);
        BeanUtils.copyProperties(userDTO, user);
        user.setIngestionOperation(IngestionOperation.CSV);
        user.setStatus(Status.ACTIVE);
        user.setPassword("123456");
        user.setRole(RoleEnum.USER.name());
//        user.setCompany();


        userRepository.save(user);

        return Optional.of(userDTO);
    }
}
