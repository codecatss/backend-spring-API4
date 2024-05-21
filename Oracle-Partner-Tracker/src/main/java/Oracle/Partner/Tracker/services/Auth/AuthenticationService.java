package Oracle.Partner.Tracker.services.Auth;

import java.util.List;

import javax.naming.AuthenticationException;

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

        String email = authentication.email();
        
        if( emailService.validadeEmail(email)){
            passwordService.validatePassword(email, authentication.password());
        }


        //TODO: Implementar a verificação da senha
        try {
                return jwtService.generateToken(authentication);
        } catch (Exception e) {
            throw new ServiceException("Erro no serviço jwtService:\n", e);
        }
    }
}
