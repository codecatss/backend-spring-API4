package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.DashboardDTO;
import Oracle.Partner.Tracker.dto.StatePerCompany;
import Oracle.Partner.Tracker.dto.TrackPerCompany;
import Oracle.Partner.Tracker.repositories.CompanyRepository;
import Oracle.Partner.Tracker.repositories.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final ExpertiseRepository expertiseRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public DashboardService(ExpertiseRepository expertiseRepository, CompanyRepository companyRepository) {
        this.expertiseRepository = expertiseRepository;
        this.companyRepository = companyRepository;
    }

    public DashboardDTO getAll(){
        List<Object[]> kpis = expertiseRepository.getDashboardDTO();
        DashboardDTO data = new DashboardDTO();

        for(Object[] obj : kpis){
            data.setQtyPartners(Integer.parseInt(String.valueOf(obj[0])));
            data.setQtyPartnersActive(Integer.parseInt(String.valueOf(obj[1])));
            data.setQtyPartnersInactive(Integer.parseInt(String.valueOf(obj[2])));
            data.setAverageTracksPerPartners((BigDecimal) obj[3]);
            data.setQtyUsers(Integer.parseInt(String.valueOf(obj[4])));
            data.setQtyTracks(Integer.parseInt(String.valueOf(obj[5])));
            data.setQtyExpertise(Integer.parseInt(String.valueOf(obj[6])));
        }
        return data;
    }

    public List<TrackPerCompany> getTrackPerCompany(){
        return companyRepository.getTrackPerCompany();
    }

    public List<StatePerCompany> getStatePerCompany(){
        return companyRepository.getCompaniesByState();
    }
}
