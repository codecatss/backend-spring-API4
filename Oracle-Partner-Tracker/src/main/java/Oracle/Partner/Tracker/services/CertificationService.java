package Oracle.Partner.Tracker.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Oracle.Partner.Tracker.dto.CertificationDTO;
import Oracle.Partner.Tracker.entities.Certification;
import Oracle.Partner.Tracker.repositories.CertificationRepository;

@Service
public class CertificationService {
    
    @Autowired
    private CertificationRepository certificationRepository;

    public List<CertificationDTO> getAllCertifications() {
        List<Certification> certifications = certificationRepository.findAll();
        return certifications.stream().map(this::convertToDto).collect(Collectors.toList());
    };

    private CertificationDTO convertToDto(Certification certification) {
        CertificationDTO dto = new CertificationDTO();
        dto.setName(certification.getName());
        dto.setDescription(certification.getDescription());
        dto.setIngestionOperation(certification.getIngestionOperation());
        return dto;
    };

    public CertificationDTO getCertificationById(Long id) {
        Optional<Certification> certification = certificationRepository.findById(id);
        return certification.map(this::convertToDto).orElse(null);
    };

    public CertificationDTO createCertification(CertificationDTO certificationDTO) {
        Certification certification = convertToEntity(certificationDTO);
        Certification savedCertification = certificationRepository.save(certification);

        return convertToDto(savedCertification);
    };

    private Certification convertToEntity(CertificationDTO certificationDTO) {
        Certification certification = new Certification();
        certification.setName(certificationDTO.getName());
        certification.setDescription(certificationDTO.getDescription());
        certification.setIngestionOperation(certificationDTO.getIngestionOperation());
        certification.setEmployeeCertification(new ArrayList<>());
        certification.setExpertiseCertification(new ArrayList<>());
        certification.setCreateAt(LocalDateTime.now());
        certification.setLifeTimeMonth(null);

        return certification;
    };
}
