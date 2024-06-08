package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.DashboardDTO;
import Oracle.Partner.Tracker.dto.TrackPerCompany;
import Oracle.Partner.Tracker.entities.CompanyExpertiseUserCount;
import Oracle.Partner.Tracker.dto.StatePerCompany;
import Oracle.Partner.Tracker.repositories.CompanyRepository;
import Oracle.Partner.Tracker.services.CompanyExpertiseUserCountService;
import Oracle.Partner.Tracker.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/dash")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private CompanyExpertiseUserCountService companyExpertiseUserCountService;

    @GetMapping
    public ResponseEntity<DashboardDTO> getAllKPI() {

        DashboardDTO data = dashboardService.getAll();
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/track-per-company")
    public ResponseEntity<List<TrackPerCompany>> getTrackPerCompany() {

        List<TrackPerCompany> data = dashboardService.getTrackPerCompany();
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/state-per-company")
    public ResponseEntity<List<StatePerCompany>> getStatePerCompany() {
        List<StatePerCompany> data = dashboardService.getStatePerCompany();
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/opntrack/visualization")
    public Map<Integer, Map<String, String>> getOpnTrackUsageCount() {
        return dashboardService.getOpnTrackUsageCount();
    }

    @GetMapping(path = "/expertise/visualization")
    public Map<Integer, Map<String, String>> getExpertiseUsageCount() {
        return dashboardService.getExpertiseUsageCount();
    }

    @GetMapping(value = "/certification-per-user")
    public List<Object[]> getUserCertification() {
        return dashboardService.getCertificationsNearExpiration(90);
    }


    @GetMapping(value = "/companyexpertiseusercountservice")
    public List<CompanyExpertiseUserCount> getCompanyExpertiseUserCountService() {
        return companyExpertiseUserCountService.findAllCompanies();
    }

    @Autowired
    CompanyRepository companyRepository;


    @GetMapping(value = "/completeworkloads")
    public ResponseEntity<List<Map<String, Object>>> getCompaniesWithCompleteWorkloads() {
        List<Object[]> data = companyRepository.findCompaniesGroupedByWorkloadAndExpertise();
        if (data == null || data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] row : data) {
            String workloadName = (String) row[0];
            String expertiseName = (String) row[1];

            // Procura se já existe um objeto com o nome do workload na lista
            Optional<Map<String, Object>> existingWorkload = resultList.stream()
                    .filter(workload -> workload.get("name").equals(workloadName))
                    .findFirst();

            // Se já existir, adiciona a expertise à lista de expertise desse workload
            if (existingWorkload.isPresent()) {
                Map<String, Object> workload = existingWorkload.get();
                List<String> expertiseList = (List<String>) workload.get("expertises");
                expertiseList.add(expertiseName);
            } else { // Se não existir, cria um novo objeto para o workload
                Map<String, Object> newWorkload = new HashMap<>();
                newWorkload.put("name", workloadName);
                List<String> expertiseList = new ArrayList<>();
                expertiseList.add(expertiseName);
                newWorkload.put("expertises", expertiseList);
                resultList.add(newWorkload);
            }
        }

        return ResponseEntity.ok(resultList);
    }

    @GetMapping(value = "/finance-income")
    public List<Object[]> getAllCompanyAnalysis() {
        return dashboardService.getAllCompanyAnalysis();
    }

    @GetMapping(value = "/finance-expenses")
    public List<Object[]> getExpenses() {
        return dashboardService.getAllCertificationsAnalysisExpired();
    }

    @GetMapping(value = "/finance-profit")
    public List<Object[]> getProfit() {
        return dashboardService.getAllCertificationsAnalysisInProgress();
    }

}