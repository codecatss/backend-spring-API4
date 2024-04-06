package Oracle.Partner.Tracker.controllers;

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
