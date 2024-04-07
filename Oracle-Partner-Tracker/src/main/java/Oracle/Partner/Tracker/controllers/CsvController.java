package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.services.CsvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class CsvController {

    private final CsvService csvService;

    public CsvController(CsvService csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/api/import-csv")
    public ResponseEntity<List<CompanyDTO>> importCsv(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

//        List<CompanyDTO> companies = csvService.processCsv(file);
        List<CompanyDTO> companies = null;
        List<ExpertiseDTO> expertises = csvService.processCsvExpertise(file);

        if (companies != null) {
            return ResponseEntity.ok(companies);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
