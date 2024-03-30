package Oracle.Partner.Tracker.controllers;


import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;


    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);

    }
}
