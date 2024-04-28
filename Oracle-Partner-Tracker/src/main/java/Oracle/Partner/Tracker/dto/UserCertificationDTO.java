package Oracle.Partner.Tracker.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCertificationDTO {
    private String userName;
    private String certificationName;
    private long daysUntilExpiration;
    private LocalDateTime expirationDate;

}