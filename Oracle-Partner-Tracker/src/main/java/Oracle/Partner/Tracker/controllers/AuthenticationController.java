package Oracle.Partner.Tracker.controllers;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import Oracle.Partner.Tracker.dto.AuthDTO;
import Oracle.Partner.Tracker.services.AuthenticationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.util.MultiValueMap;

@RestController
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> authenticate(@RequestBody AuthDTO authentication) throws Exception {
        
        System.out.println();
        System.out.println(new ObjectMapper().writeValueAsString(authentication));
        System.out.println();

        try {
            List<String> response = authenticationService.authenticate(authentication);
            if (response != null && !response.contains("FALSE")) {

                MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
                formData.add("token", response.get(0));
                formData.add("userName", response.get(1));

                return ResponseEntity.created(null).body(formData);
            } else {
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (AuthenticationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
