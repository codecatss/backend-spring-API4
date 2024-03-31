package Oracle.Partner.Tracker.controllers;


import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.repositories.CompanyRepository;
import Oracle.Partner.Tracker.services.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;

    @GetMapping
    @Operation( summary = "Company", description = "Get all companies" )
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(
                    array = @ArraySchema(
                        schema = @Schema(implementation = Company.class)
                    )
                ),
                description = "Companies retrieved"
                
                ),
            @ApiResponse(responseCode = "404", description = "Companies not found")
    })
    public ResponseEntity<Page<CompanyDTO>> getAllCompanies(Pageable pageable) {
        Page<CompanyDTO> companies = companyService.findAllCompanies(pageable);
        return new ResponseEntity<>(companies, HttpStatus.OK);

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
    public ResponseEntity<CompanyDTO> findCompanyById(@PathVariable String id) {
        CompanyDTO companyDTO = companyService.findCompanyById(id);
        if (companyDTO != null) {
            return new ResponseEntity<>(companyDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping
    public CompanyDTO insertCompany(@RequestBody CompanyDTO companyDTO){
        return companyService.insertCompany(companyDTO);
    }



}
