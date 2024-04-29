package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.repositories.UserCertificationRepository;
import Oracle.Partner.Tracker.dto.UserCertificationDTO;
import Oracle.Partner.Tracker.entities.relations.UserCertification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCertificationService {

    @Autowired
    private UserCertificationRepository userCertificationRepository;

    public List<Object[]> getCertificationsNearExpiration(int daysThreshold) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime expirationDateThreshold = currentDate.plusDays(daysThreshold);
        return userCertificationRepository.getUserCertifications(currentDate, expirationDateThreshold);
    }
    
    

    }

