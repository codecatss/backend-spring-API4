package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.UserDTO;
import Oracle.Partner.Tracker.entities.User;
import Oracle.Partner.Tracker.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id).get();
    }

    public User registerNewtUser(UserDTO user) {
        if (userRepository.existsByEmail(user.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        return userRepository.save(newUser);
    }

    public User updateUser(UUID id, UserDTO user){
        Optional<User> userUpdate = userRepository.findById(id);
        if (userUpdate.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if ()

    }

    public void deleteUser(UUID id) {
        if (findUserById(id).getId() == null){
            throw new IllegalArgumentException("ID não encontrado");
        }
        userRepository.deleteById(id);
    }
}
