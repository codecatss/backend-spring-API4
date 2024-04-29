package Oracle.Partner.Tracker.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import Oracle.Partner.Tracker.utils.CertificationStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCertificationDTO {
    private String userName;
    private String companyName;
    private String certificationName;
    private LocalDateTime expirationDate;
    private CertificationStatus certificationStatus;
    private long daysUntilExpiration;

}

