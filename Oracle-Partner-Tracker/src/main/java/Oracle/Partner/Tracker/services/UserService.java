package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.dto.GenericDTO;
import Oracle.Partner.Tracker.dto.UserDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.entities.User;
import Oracle.Partner.Tracker.repositories.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private CompanyService companyService;

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
//        newUser.setStatus(Status.ACTIVE);
//        newUser.setIngestionOperation(IngestionOperation.MANUAL);
//        BeanUtils.copyProperties(user, newUser);
        return userRepository.save(new User(user));
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
    public Class<?> getDtoClass() {
        return UserDTO.class;
    }

    @Override
    public void saveAllGenericDTO(List<GenericDTO> genericDTOList) {
        for(GenericDTO genericDTO : genericDTOList){
            UserDTO userDTO = (UserDTO) genericDTO;
            CompanyDTO companyDTO = companyService.findCompanyByCnpj(userDTO.getCnpjCompanyString());
            Company company = new Company(companyDTO);
            company.setId(companyDTO.getId());
            userDTO.setCompany(company);
            User user = new User(userDTO);
            System.out.println(user.getRole());
            userRepository.save(user);
        }
    }
}
