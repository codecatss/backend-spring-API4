package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.entities.relations.ExpertiseCertification;
import Oracle.Partner.Tracker.entities.relations.EmployeeCertification;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "certification")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "life_time_month")
    private Integer lifeTimeMonth;

    @Column(name = "ingestion_operation")
    @Enumerated(EnumType.STRING)
    private IngestionOperation ingestionOperation;

    @JsonIgnore
    @OneToMany(mappedBy = "certification")
    private List<EmployeeCertification> employeeCertification = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "certification")
    private List<ExpertiseCertification> expertiseCertification = new ArrayList<>();

    public void addUserCertificartion(EmployeeCertification userCertificartion){
        userCertificartion.setCertification(this);
        this.employeeCertification.add(userCertificartion);
    }

    public void addExpertiseCertification(ExpertiseCertification expertiseCertification){
        expertiseCertification.setCertification(this);
        this.expertiseCertification.add(expertiseCertification);
    }
}