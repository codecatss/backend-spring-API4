package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.DashboardDTO;
import Oracle.Partner.Tracker.dto.StatePerCompany;
import Oracle.Partner.Tracker.dto.TrackPerCompany;
import Oracle.Partner.Tracker.repositories.CompanyRepository;
import Oracle.Partner.Tracker.repositories.ExpertiseRepository;
import Oracle.Partner.Tracker.repositories.OpnTrackRepository;
import Oracle.Partner.Tracker.repositories.EmployeeCertificationsRepository;
import Oracle.Partner.Tracker.utils.DashboardColorEnum;

import Oracle.Partner.Tracker.utils.MapObject;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


@Service
public class DashboardService {

    @Autowired
    private ExpertiseRepository expertiseRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private OpnTrackRepository opnTrackRepository;

    @Autowired
    private EmployeeCertificationsRepository employeeCertificationsRepository;

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
            Long lastMonthCount = (Long) obj[7];
            Long monthCount = (Long) obj[8];
            Double growthPercentage = ((double) monthCount - lastMonthCount) / lastMonthCount * 100;
            data.setQtygrowth(growthPercentage);
        }
        return data;
    }

    public List<TrackPerCompany> getTrackPerCompany(){
        return companyRepository.getTrackPerCompany();
    }

    public List<StatePerCompany> getStatePerCompany(){
        return companyRepository.getCompaniesByState();
    }

    public Map<Integer, Map<String, String>> getOpnTrackUsageCount(){
        String[] columnsName = {"title", "amount"};
        Map<String, String> additionalInformation = new HashMap<>();
        additionalInformation.put("avatarIcon", "bx-bar-chart-alt-2");

        return MapObject.mapObjectList(opnTrackRepository.getOpnTrackUsageCount(), columnsName, additionalInformation);

    }

    public Map<Integer, Map<String, String>> getExpertiseUsageCount(){
        String[] columnsName = {"title", "amount"};
        Map<String, String> additionalInformation = new HashMap<>();
        additionalInformation.put("avatarIcon", "bx-bar-chart-alt-2");

        return MapObject.mapObjectList(expertiseRepository.getExpertiseUsageCount(), columnsName, additionalInformation);
    }

    @Autowired
    private EmployeeCertificationsRepository employeeCertificationsRepository;


    public List<Object[]> getCertificationsNearExpiration(int daysThreshold) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime expirationDateThreshold = currentDate.plusDays(daysThreshold);
        return employeeCertificationsRepository.getEmployeeCertifications(currentDate, expirationDateThreshold);
    }
}