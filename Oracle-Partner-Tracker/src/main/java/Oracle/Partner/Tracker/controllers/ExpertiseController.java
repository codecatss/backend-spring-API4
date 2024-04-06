package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.dto.UserDTO;
import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.entities.User;
import Oracle.Partner.Tracker.repositories.ExpertiseRepository;
import Oracle.Partner.Tracker.services.ExpertiseService;
import Oracle.Partner.Tracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "/expertise")
public class ExpertiseController {
    @Autowired
    ExpertiseRepository expertiseRepository;

    @Autowired
    ExpertiseService expertiseService;

    @GetMapping
    public List<Expertise> getAllExpertise(){
        return expertiseService.findAllExpertise();
    }

    @PostMapping
    public void addNewExpertise(ExpertiseDTO expertiseDTO){
        expertiseService.saveExpertise(expertiseDTO);
    }
}
