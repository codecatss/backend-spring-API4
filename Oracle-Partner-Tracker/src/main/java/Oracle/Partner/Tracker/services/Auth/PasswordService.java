package Oracle.Partner.Tracker.services.Auth;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Oracle.Partner.Tracker.repositories.PartnerRepository;

@Service
public class PasswordService {

    @Autowired
    PartnerRepository partnerRepository;
    
    public boolean validatePassword(String email, String password) throws AuthenticationException {
        if(password == null || password.isBlank()) {
            throw new AuthenticationException("Senha null ou vazio");
        }
        if (partnerRepository.findByEmail(email).getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
