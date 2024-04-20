package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.util.IngestionOperation;
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
    private List<UserCertificartion> certificartions = new ArrayList<>();
}