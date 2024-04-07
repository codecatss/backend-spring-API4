package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.dto.OpnTrackDTO;
import Oracle.Partner.Tracker.services.CompanyCsvService;
import Oracle.Partner.Tracker.services.OpnTrackCsvService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CsvController {

    @Autowired
    private CompanyCsvService companyCsvService;
    
    @Autowired
    private OpnTrackCsvService opnTrackCsvService;

    @PostMapping("/api/import-csv")
    public ResponseEntity<Map<String, List<?>>> importCsv(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        List<OpnTrackDTO> opnTracks = opnTrackCsvService.processCsv(file);
        List<CompanyDTO> companies = companyCsvService.processCsv(file);
        
        
        Map<String, List<?>> response = new HashMap<>();
        response.put("companies", companies);
        response.put("opnTracks", opnTracks);
    
        return ResponseEntity.ok(response);
    }
}
