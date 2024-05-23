package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.ExpertiseRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.services.ExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping(value = "/expertise")
public class ExpertiseController {
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

    @PostMapping(value = "/save")
    public void saveNewExpertise(@RequestBody ExpertiseRecord expertiseRecord){
        expertiseService.saveExpertise(expertiseRecord);
    }
}
