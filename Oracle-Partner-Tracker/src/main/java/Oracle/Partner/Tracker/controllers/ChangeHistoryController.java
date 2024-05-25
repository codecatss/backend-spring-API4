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
    public ResponseEntity<Map<Integer, Map<String, String>>> getAllFromChangeHistoryView(){
        return ResponseEntity.ok(changeHistoryService.getAllFromChangeHistoryView());
    }

    @GetMapping(value = "/data/grouped")
    public ResponseEntity<Map<Integer, Map<String, String>>> getAllGroupByFromChangeHistoryView(){
        return ResponseEntity.ok(changeHistoryService.getAllGroupByFromChangeHistoryView());
    }

    @GetMapping(value = "/data/{tableAndId}")
    public ResponseEntity<Map<Integer, Map<String, String>>> getByIdFromChangeHistoryView(@PathVariable String tableAndId){
        String[] parts = tableAndId.split("-");
        String tableName = parts[0];
        String id = parts[1];
        return ResponseEntity.ok(changeHistoryService.getByIdFromChangeHistoryView(tableName, id));
    }
}
