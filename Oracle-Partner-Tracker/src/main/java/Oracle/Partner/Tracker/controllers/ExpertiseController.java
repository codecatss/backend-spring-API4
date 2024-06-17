package Oracle.Partner.Tracker.controllers;


import Oracle.Partner.Tracker.dto.ExpertiseRecord;
import Oracle.Partner.Tracker.services.ChangeHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.entities.Expertise;

import Oracle.Partner.Tracker.services.ExpertiseService;
import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/expertise")
public class ExpertiseController {

    @Autowired
    private ExpertiseService expertiseService;

    @Autowired
    private ChangeHistoryService changeHistoryService;

    @GetMapping
    public List<Expertise> getAllExpertise(){
        return expertiseService.findAllExpertise();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Expertise> getExpertise(@PathVariable Long id){
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

    @PutMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody ExpertiseDTO expertiseDTO){
        expertiseService.updateExpertise(id, expertiseDTO);
    }
}
