package Oracle.Partner.Tracker.controllers;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

import Oracle.Partner.Tracker.dto.AuthDTO;
import Oracle.Partner.Tracker.services.Auth.AuthenticationService;

import org.springframework.util.MultiValueMap;

@RestController
@CrossOrigin
@RequestMapping(value = "/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<Object> authenticate(@RequestBody AuthDTO authentication) throws Exception {
        System.out.println(authentication);
        try {
            List<String> response = authenticationService.authenticate(authentication);

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();

            if (response != null && !response.get(1).contains("FALSE")) {

                formData.add("token", response.get(0));
                formData.add("userName", response.get(1));
                formData.add("userRole", response.get(2));

                return ResponseEntity.created(null).body(formData);
            } else {

                if (response.get(0).contains("existsByEmail")) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"existsByEmail\": \"" + "FALSE" + "\"}");
                }
                if (response.get(0).contains("isPasswordValid")) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"isPasswordValid\": \"" + "FALSE" + "\"}");
                }
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"" + "Erro interno" + "\"}");
                
            }
        } catch (AuthenticationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
