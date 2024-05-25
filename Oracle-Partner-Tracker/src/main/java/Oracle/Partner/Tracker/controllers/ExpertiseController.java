package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.entities.Partner;
import Oracle.Partner.Tracker.services.ChangeHistoryService;
import Oracle.Partner.Tracker.utils.ChangeType;
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<ExpertiseDTO> updateCompany(@PathVariable Long id, @RequestBody ExpertiseDTO companyDTO) {
        ExpertiseDTO oldExpertiseDTO = expertiseService.findExpertiseDtoById(id);
        expertiseService.updateExpertise(id, companyDTO);
        Partner partner = new Partner();
        partner.setId(Long.decode("1"));
        changeHistoryService.saveChangeHistory(partner, id, "expertise", ChangeType.UPDATE, oldExpertiseDTO, companyDTO);
        return ResponseEntity.ok(new ExpertiseDTO());
    }
}
