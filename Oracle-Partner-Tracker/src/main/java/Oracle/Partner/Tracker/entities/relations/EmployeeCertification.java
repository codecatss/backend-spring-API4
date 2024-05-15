package Oracle.Partner.Tracker.entities.relations;

import Oracle.Partner.Tracker.entities.Certification;
import Oracle.Partner.Tracker.entities.Employee;
import Oracle.Partner.Tracker.utils.CertificationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "employee_certification")
public class EmployeeCertification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
    @Enumerated(EnumType.STRING)
    @Column(name= "status")
    private CertificationStatus status;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certification_id")
    private Certification certification;
}
