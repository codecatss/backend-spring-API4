package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.DashboardDTO;
import Oracle.Partner.Tracker.dto.TrackPerCompany;
import Oracle.Partner.Tracker.dto.UserCertificationDTO;
import Oracle.Partner.Tracker.entities.relations.UserCertification;
import Oracle.Partner.Tracker.dto.StatePerCompany;
import Oracle.Partner.Tracker.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/dash")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardDTO> getAllKPI(){

        DashboardDTO data = dashboardService.getAll();
        if(data == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(data);
    }

    @GetMapping(value="/track-per-company")
    public ResponseEntity<List<TrackPerCompany>> getTrackPerCompany(){

        List<TrackPerCompany> data = dashboardService.getTrackPerCompany();
        if(data == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(data);
    }

    @GetMapping(value="/state-per-company")
    public ResponseEntity<List<StatePerCompany>> getStatePerCompany(){
        List<StatePerCompany> data = dashboardService.getStatePerCompany();
        if(data == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(data);
    }

    @GetMapping(value="/certification-per-user")
    public ResponseEntity<List<UserCertificationDTO>> getUserCertification(){
        List<UserCertificationDTO> data = dashboardService.getCertificationsNearExpiration(90);
        if(data == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(data);
    }

    @GetMapping(path = "/opntrack/visualization")
    public Map<Integer, Map<String, String>> getOpnTrackUsageCount(){
        return dashboardService.getOpnTrackUsageCount();
    }
}
