package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.DashboardDTO;
import Oracle.Partner.Tracker.dto.TrackPerCompany;
import Oracle.Partner.Tracker.dto.StatePerCompany;
import Oracle.Partner.Tracker.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(path = "/opntrack/visualization")
    public String getTesteAPI(){
        String toReturn = """
                {
                  "1": {
                    "subtitle": "Cloud Build for build",
                    "amount": "82",
                    "title": "Cloud Build",
                    "avatarColor": "success",
                    "avatarIcon": "bx-bar-chart-alt-2"
                  },
                  "2": {
                    "subtitle": "Cloud Sell for sell",
                    "amount": "70",
                    "title": "Cloud Sell",
                    "avatarColor": "primary",
                    "avatarIcon": "bx-bar-chart-alt-2"
                  },
                  "3": {
                    "subtitle": "Cloud Service for service",
                    "amount": "50",
                    "avatarColor": "secondary",
                    "title": "Cloud Service",
                    "avatarIcon": "bx-bar-chart-alt-2"
                  },
                  "5":{
                    "subtitle": "License and Hardware for computer",
                    "amount": "45",
                    "title": "License and Hardware",
                    "avatarColor": "info",
                    "avatarIcon": "bx-bar-chart-alt-2"
                  }
                }
                """;
        return toReturn;
    }

}
