package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.entities.CompanyExpertiseUserCount;
import Oracle.Partner.Tracker.repositories.CompanyExpertiseUserCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyExpertiseUserCountService {


    @Autowired
    private CompanyExpertiseUserCountRepository companyExpertiseUserCountRepository;

    public List<CompanyExpertiseUserCount> findAllCompanies(){
        List<CompanyExpertiseUserCount> companies = companyExpertiseUserCountRepository.findAll();
        return companies;
    }
}