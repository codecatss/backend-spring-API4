package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.services.CsvService;
import Oracle.Partner.Tracker.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CsvController {

    @Autowired
    private CsvService csvService;

    @PostMapping("/api/import-csv/old_version")
    public ResponseEntity<Map<String, List<?>>> importCsvOld(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Map<String, List<?>> response = new HashMap<>();

//        for (CsvService<?> csvService : csvServices) {
//            List<?> entities = csvService.processCsv(file);
//            response.put(csvService.getClass().getSimpleName(), entities);
//        }
//
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/import-csv")
    public ResponseEntity<?> importCsv(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        csvService.mapCsvToEntities(file);

        return ResponseEntity.ok().build();
    }
}
