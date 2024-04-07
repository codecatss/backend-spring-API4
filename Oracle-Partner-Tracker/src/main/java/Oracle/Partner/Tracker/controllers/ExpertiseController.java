package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.services.ExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import Oracle.Partner.Tracker.repositories.ExpertiseRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/expertise")
public class ExpertiseController {
    @Autowired
    private ExpertiseRepository expertiseRepository;

    @Autowired
    private ExpertiseService expertiseService;

    @GetMapping
    public List<Expertise> getAllExpertise(){
        return expertiseService.findAllExpertise();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Expertise> getExpertise(@PathVariable String id){
        Expertise expertise = expertiseService.findExpertiseById(id);
        return expertise == null ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<>(expertise, HttpStatus.OK);
    }

    @PostMapping
    public void addNewExpertise(@RequestBody ExpertiseDTO expertiseDTO){
        expertiseService.saveExpertise(expertiseDTO);
    }
}
