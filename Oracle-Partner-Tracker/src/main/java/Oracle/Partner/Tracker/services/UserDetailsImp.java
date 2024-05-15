package Oracle.Partner.Tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import Oracle.Partner.Tracker.entities.User;
import Oracle.Partner.Tracker.infra.security.UserAuthenticated;
import Oracle.Partner.Tracker.repositories.UserRepository;

public class UserDetailsImp implements UserDetailsService{

    private final UserRepository userRepository;
    public UserDetailsImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usrToLog = userRepository.findByName(username);
        if (usrToLog != null) {
            return new UserAuthenticated(usrToLog);
        }
        throw new UsernameNotFoundException("User not found");
    }
    
}
