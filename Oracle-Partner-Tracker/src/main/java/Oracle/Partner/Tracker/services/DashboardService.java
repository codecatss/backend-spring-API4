package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.DashboardDTO;
import Oracle.Partner.Tracker.dto.StatePerCompany;
import Oracle.Partner.Tracker.dto.TrackPerCompany;
import Oracle.Partner.Tracker.dto.UserCertificationDTO;
import Oracle.Partner.Tracker.entities.relations.UserCertification;
import Oracle.Partner.Tracker.repositories.CompanyRepository;
import Oracle.Partner.Tracker.repositories.ExpertiseRepository;
import Oracle.Partner.Tracker.repositories.OpnTrackRepository;
import Oracle.Partner.Tracker.repositories.UserCertificationRepository;
import Oracle.Partner.Tracker.utils.DashboardColorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Service
public class DashboardService {
    @Autowired
    private ExpertiseRepository expertiseRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private OpnTrackRepository opnTrackRepository;

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

    public Map<Integer, Map<String, String>> getOpnTrackUsageCount(){
        String[] columnsName = {"title", "amount"};
        Map<String, String> additionalInformation = new HashMap<>();
        additionalInformation.put("avatarIcon", "bx-bar-chart-alt-2");
        return mapObjectList(opnTrackRepository.getOpnTrackUsageCount(), columnsName, additionalInformation);
    }

    public Map<Integer, Map<String, String>> getExpertiseUsageCount(){
        String[] columnsName = {"title", "amount"};
        Map<String, String> additionalInformation = new HashMap<>();
        additionalInformation.put("avatarIcon", "bx-bar-chart-alt-2");
        return mapObjectList(expertiseRepository.getExpertiseUsageCount(), columnsName, additionalInformation);
    }

    private Map<Integer, Map<String, String>> mapObjectList(List<Object[]> objectList, String[] columnsName, Map<String, String> additionalInformation){
        List<DashboardColorEnum> listOfColorsAlreadyUsed = new ArrayList<>();
        DashboardColorEnum color = DashboardColorEnum.getRandom();
        Map<Integer, Map<String, String>> queryDataMap = new HashMap<>();
        int cont = 1;
        for(Object[] object : objectList){
            Map<String, String> mapOfObject = new HashMap<>();
            for (int i = 0; i < object.length; i++) {
                mapOfObject.put(columnsName[i], object[i].toString());
            }
            if (additionalInformation != null) {
                mapOfObject.putAll(additionalInformation);
            }
            mapOfObject.put("avatarColor", DashboardColorEnum.getValueFormated(DashboardColorEnum.getRandom()));
            queryDataMap.put(cont++, mapOfObject);
            listOfColorsAlreadyUsed.add(color);
            color = DashboardColorEnum.getRandomColorDifferentOf(listOfColorsAlreadyUsed);
        }
        return queryDataMap;
    }
    
    
}