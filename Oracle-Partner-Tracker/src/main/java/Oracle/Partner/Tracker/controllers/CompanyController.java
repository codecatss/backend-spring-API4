package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.dto.CompanyRecord;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.services.ChangeHistoryService;
import Oracle.Partner.Tracker.services.CompanyService;
import Oracle.Partner.Tracker.utils.ChangeType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ChangeHistoryService changeHistoryService;

    @GetMapping
    public ResponseEntity<Map<Integer, Map<String, String>> > getAllCompanies() {
        Map<Integer, Map<String, String>>  companies = companyService.findAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping(value = "/active")
    public ResponseEntity<List<Company>> getAllCompaniesActive() {
        List<Company> companies = companyService.findAllCompiniesActive();
        return ResponseEntity.ok(companies);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find Company by ID", description = "Get a company by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(
                            schema = @Schema(implementation = CompanyDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Company not found"
            )
    })
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        CompanyDTO companyDTO = companyService.getCompanyById(id);
        if (companyDTO != null) {
            return new ResponseEntity<>(companyDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Insert Company", description = "Insert a new company")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Company created",
                    content = @Content(
                            schema = @Schema(implementation = CompanyDTO.class)
                    )
            )
    })
    public ResponseEntity<CompanyDTO> insertCompany(@RequestBody CompanyDTO companyDTO) {
        companyDTO = companyService.insertCompany(companyDTO).get();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(companyDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(companyDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        CompanyDTO oldCompanyDTO = companyService.getCompanyById(id);
        CompanyDTO newCompanyDTO = companyService.updateCompany(id, companyDTO);
//        changeHistoryService.saveChangeHistory(Long.decode("1"), id, "company", ChangeType.UPDATE, oldCompanyDTO, newCompanyDTO);
        return ResponseEntity.ok(newCompanyDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> disableCompany(@PathVariable Long id) {
        companyService.disableCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/enable")
    public ResponseEntity<Void> enableCompany(@PathVariable Long id) {
        companyService.enableCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/save")
    public ResponseEntity company(@RequestBody CompanyRecord companyRecord){
        companyService.saveCompany(companyRecord);
        return ResponseEntity.ok().build();
    }
}
