package Oracle.Partner.Tracker.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class AuthenticationService {
    private final JwtService jwtService;
    
    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication) {
        log.info("Tentativa de login com: " + authentication.getName());
        return jwtService.generateToken(authentication);
    }
}
