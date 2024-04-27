package Oracle.Partner.Tracker.entities.relations;

import Oracle.Partner.Tracker.entities.Certification;
import Oracle.Partner.Tracker.entities.User;
import Oracle.Partner.Tracker.utils.Status;
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
@Table(name = "user_certification")
public class UserCertificartion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "epiration_date")
    private LocalDateTime expirationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certification_id")
    private Certification certification;
}
