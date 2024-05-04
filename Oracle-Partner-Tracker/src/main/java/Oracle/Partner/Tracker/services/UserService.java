package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.GenericDTO;
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
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements GenericService{

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
        if (userRepository.existsByEmail(user.getEmail())){
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
    public void mapCsvToEntities(List<String[]> csvData) {

    }

    @Override
    public Class<?> getDtoClass() {
        return UserDTO.class;
    }

    @Override
    public void saveAllGenericDTO(List<GenericDTO> genericDTOList) {

    }
}
