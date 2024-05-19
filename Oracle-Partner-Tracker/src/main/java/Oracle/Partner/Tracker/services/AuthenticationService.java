package Oracle.Partner.Tracker.services;

import java.util.Arrays;
import java.util.List;

import javax.naming.AuthenticationException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Oracle.Partner.Tracker.dto.AuthDTO;
import Oracle.Partner.Tracker.repositories.PartnerRepository;
@Service
public class AuthenticationService {

    @Autowired
    PartnerRepository partnerRepository;

    private final JwtService jwtService;
    
    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public List<String> authenticate(AuthDTO authentication) throws Exception{

        if (authentication.email() == null || authentication.email().isBlank()) {
            throw new AuthenticationException("Email null ou blank");
        }

        if (authentication.password() == null || authentication.password().isBlank()) {
            throw new AuthenticationException("Senha null ou blank");
        }

        if(partnerRepository.existsByEmail(authentication.email()) == false) {
            return Arrays.asList("{'existsByEmail':'FALSE'}");
        }

        //TODO: Implementar a verificação da senha
        try {
            return jwtService.generateToken(authentication);
        } catch (Exception e) {
            throw new ServiceException("Erro no serviço jwtService:\n", e);
        }
    }
}
