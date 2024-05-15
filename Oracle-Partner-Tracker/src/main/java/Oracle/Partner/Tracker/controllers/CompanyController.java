package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.services.CompanyService;
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

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    //@Operation(summary = "Company", description = "Get all companies")
    //@ApiResponses(value = {
    //        @ApiResponse(
    //                responseCode = "200",
    //                content = @Content(
    //                        array = @ArraySchema(
    //                                schema = @Schema(implementation = Company.class)
    //                        )
    //                ),
    //                description = "Companies retrieved"
    //        ),
    //        @ApiResponse(responseCode = "404", description = "Companies not found")
    //})
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.findAllCompanies();
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
        companyDTO = companyService.updateCompany(id, companyDTO);
        return ResponseEntity.ok(companyDTO);
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
}
