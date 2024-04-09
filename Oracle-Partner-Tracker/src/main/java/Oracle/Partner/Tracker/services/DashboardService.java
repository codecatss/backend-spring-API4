package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.DashboardDTO;
import Oracle.Partner.Tracker.repositories.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ExpertiseRepository expertiseRepository;

    public DashboardDTO getAll(){
        List<Object[]> kpis = expertiseRepository.getAllKPIs();
        DashboardDTO data = new DashboardDTO();

        for(Object[] obj : kpis){
            data.setQtyPartners((Integer) obj[0]);
            data.setQtyPartnersActive((Integer) obj[1]);
            data.setQtyPartnersInactive((Integer) obj[2]);
            data.setAverageTracksPerPartners((BigDecimal) obj[3]);
            data.setQtyUsers((Integer) obj[4]);
            data.setQtyTracks((Integer) obj[5]);
            data.setQtyExpertise((Integer) obj[6]);
            data.setAverageExpertisePerUsers((BigDecimal) obj[7]);
        }
        return data;
    }
}
