package Oracle.Partner.Tracker.controllers;


import Oracle.Partner.Tracker.dto.PartnerDTO;
import Oracle.Partner.Tracker.entities.Partner;
import Oracle.Partner.Tracker.services.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/partner")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Partner> findbyId(@PathVariable Long id){
        return ResponseEntity.ok(partnerService.findUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<Partner>> findAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(partnerService.findAllUsers());
    }

    @PostMapping
    public ResponseEntity registerNewUser(@RequestBody PartnerDTO partnerDTO){
        return partnerService.registerNewPartner(partnerDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody PartnerDTO employeeDTO){
        return partnerService.updateUser(id, employeeDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        return partnerService.deleteUser(id);
    }
}
