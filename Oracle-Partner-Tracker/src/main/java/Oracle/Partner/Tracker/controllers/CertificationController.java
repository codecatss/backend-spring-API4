package Oracle.Partner.Tracker.controllers;

import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Oracle.Partner.Tracker.dto.CertificationDTO;
import Oracle.Partner.Tracker.entities.Certification;
import Oracle.Partner.Tracker.services.CertificationService;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping(value = "/certification")
public class CertificationController {

    private static final Logger logger = LoggerFactory.getLogger(CertificationController.class);
    
    @Autowired
    private CertificationService certificationService;

    @GetMapping
    public List<CertificationDTO> getAllCertifications() {
        return certificationService.getAllCertifications();
    };

    @GetMapping("/withId")
    public List<Certification> getAllEntities() {
        return certificationService.getAllCertificationsWithId();
    };

    @GetMapping("/{id}")
    public ResponseEntity<CertificationDTO> getCertificationById(@PathVariable Long id) {
        CertificationDTO dto = certificationService.getCertificationById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    };

    @PostMapping
    public ResponseEntity<CertificationDTO> createCertification(@RequestBody CertificationDTO certificationDTO) {
        System.out.println(certificationDTO);
        logger.info("Received CertificationDTO: {}", certificationDTO);
        CertificationDTO createdCertification = certificationService.createCertification(certificationDTO);
        logger.info("Created CertificationDTO: {}", createdCertification);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCertification);
    };

}
