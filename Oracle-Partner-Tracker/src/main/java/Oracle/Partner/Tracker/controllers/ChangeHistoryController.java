package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.entities.ChangeHistory;
import Oracle.Partner.Tracker.services.ChangeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.services.ExpertiseService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/historical")
public class ChangeHistoryController {

    @Autowired
    private ChangeHistoryService changeHistoryService;


    @GetMapping(value = "/data")
    public ResponseEntity<List<ChangeHistory>> teste(){
        List<ChangeHistory> changeHistoryList = changeHistoryService.findAll();
        return ResponseEntity.ok(changeHistoryList);
    }

    @GetMapping(value = "/data/v2")
    public ResponseEntity<List<Object[]>> testeV2(){
        List<Object[]> changeHistoryList = changeHistoryService.findAllTeste();
        return ResponseEntity.ok(changeHistoryList);
    }

}
