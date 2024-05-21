package Oracle.Partner.Tracker.services.Auth;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Oracle.Partner.Tracker.dto.AuthDTO;

@Service
public class AuthenticationService {

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordService passwordService;

    private final JwtService jwtService;
    
    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public List<String> authenticate(AuthDTO authentication) throws Exception{

        List<String> response = new ArrayList<>();

        String email = authentication.email();

        boolean generateToken = false;

        if(email != null || !email.isBlank()){

            if(emailService.validadeEmail(email)){
                generateToken = passwordService.validatePassword(email, authentication.password());

                if(!generateToken){
                    response.add("isPasswordValid");
                    response.add("FALSE");
                    return response;
                }

            } else{
                response.add("existsByEmail");
                response.add("FALSE");
                return response;
            }
        } else{
            throw new ServiceException("Email null ou blank");
        }
        
        if (generateToken) {
            try {
                return jwtService.generateToken(authentication);
            } catch (Exception e) {
                throw new ServiceException("Erro no serviço jwtService:\n", e);
            }
        } return null;
    }
}
