package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.DashboardDTO;
import Oracle.Partner.Tracker.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/dash")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardDTO> getAllKPI(){
        DashboardDTO data = dashboardService.getAll();
        return  ResponseEntity.ok(data);
    }
}
