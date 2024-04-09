package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.utils.companyEnum.IngestionOperation;

import Oracle.Partner.Tracker.util.Status;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "workload")
public class Workload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = true, length = 100, unique = true)
    private String name;

    @Column(name = "description", nullable = true, length = 250)
    private String description;

    @Column(name = "ingestion_operation")
    @Enumerated(EnumType.STRING)
    private IngestionOperation ingestionOperation;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    

}
