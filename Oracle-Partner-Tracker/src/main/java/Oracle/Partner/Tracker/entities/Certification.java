package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.entities.relations.ExpertiseCertification;
import Oracle.Partner.Tracker.entities.relations.UserCertification;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "certification")
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();
    @Column(name = "life_time_month")
    private Integer lifeTimeMonth;
    @Column(name = "ingestion_operation")
    @Enumerated(EnumType.STRING)
    private IngestionOperation ingestionOperation;
    @OneToMany(mappedBy = "certification")
    private List<UserCertification> userCertification = new ArrayList<>();
    @OneToMany(mappedBy = "certification")
    private List<ExpertiseCertification> expertiseCertification = new ArrayList<>();

    public void addUserCertificartion(UserCertification userCertificartion){
        userCertificartion.setCertification(this);
        this.userCertification.add(userCertificartion);
    }

    public void addExpertiseCertification(ExpertiseCertification expertiseCertification){
        expertiseCertification.setCertification(this);
        this.expertiseCertification.add(expertiseCertification);
    }
}