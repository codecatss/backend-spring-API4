package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public CompanyDTO findCompanyById(String id){
        Company company = companyRepository.findById(id).get();
        return new CompanyDTO(company);
    }


    @Transactional(readOnly = true)
    public List<CompanyDTO> findAllCompanies(){
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(CompanyDTO::new)
                .collect(Collectors.toList());
    }


}
