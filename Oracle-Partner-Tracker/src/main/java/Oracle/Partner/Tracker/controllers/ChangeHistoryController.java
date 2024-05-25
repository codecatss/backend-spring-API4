package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.entities.ChangeHistory;
import Oracle.Partner.Tracker.services.ChangeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.services.ExpertiseService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/historical")
public class ChangeHistoryController {

    @Autowired
    private ChangeHistoryService changeHistoryService;

    @GetMapping(value = "/data")
    public ResponseEntity<Map<Integer, Map<String, String>>> teste(){
        return ResponseEntity.ok(changeHistoryService.getAllFromChangeHistoryView());
    }

    @GetMapping(value = "/data/{tableAndId}")
    public ResponseEntity<Map<Integer, Map<String, String>>> teste2(@PathVariable String tableAndId){
        String[] parts = tableAndId.split("-");
        String tableName = parts[0];
        String id = parts[1];
        System.out.println(tableAndId);
        System.out.println(tableName);
        System.out.println(id);
        return ResponseEntity.ok(changeHistoryService.getByIdFromChangeHistoryView(tableName, id));
    }
}
