package Oracle.Partner.Tracker.services.Auth;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Oracle.Partner.Tracker.repositories.PartnerRepository;

@Service
public class EmailService {

    @Autowired
    PartnerRepository partnerRepository;


    @SuppressWarnings("null")
    public boolean validadeEmail(String email) throws Exception {

        if (email != null || !email.isBlank()) {
            if(partnerRepository.existsByEmail(email)) {
                return true;
            } else{
                return false;
            }
        } else{
            throw new AuthenticationException("Email null ou blank");
        }
    }
}
