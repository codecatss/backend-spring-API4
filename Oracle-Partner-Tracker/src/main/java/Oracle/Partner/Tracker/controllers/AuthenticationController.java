package Oracle.Partner.Tracker.controllers;

import javax.naming.AuthenticationException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import Oracle.Partner.Tracker.dto.AuthDTO;
import Oracle.Partner.Tracker.services.AuthenticationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
@RestController
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/auth")
    public String authenticate(@RequestBody AuthDTO authentication) throws AuthenticationException {
        try {
            return authenticationService.authenticate(authentication);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
