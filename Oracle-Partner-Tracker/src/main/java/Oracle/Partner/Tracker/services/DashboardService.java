package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.DashboardDTO;
import Oracle.Partner.Tracker.repositories.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ExpertiseRepository expertiseRepository;

    public DashboardDTO getAll(){
        List<Object[]> kpis = expertiseRepository.getAllKPIs();
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
}
