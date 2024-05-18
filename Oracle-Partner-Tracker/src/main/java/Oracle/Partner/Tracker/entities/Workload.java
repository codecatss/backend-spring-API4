package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.dto.WorkloadDTO;
import Oracle.Partner.Tracker.entities.relations.WorkloadExpertise;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.Status;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "ingestion_operation")
    private IngestionOperation ingestionOperation;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    @OneToMany(fetch = FetchType.LAZY)
    private List<WorkloadExpertise> workloadExpertises = new ArrayList<>();

    public Workload(WorkloadDTO workloadDTO) {
        this.name = workloadDTO.getName();
        this.description = workloadDTO.getDescription();
        this.ingestionOperation = workloadDTO.getIngestionOperation();
        this.status = workloadDTO.getStatus();
        this.createAt = workloadDTO.getCreateAt();
        this.updateAt = workloadDTO.getUpdateAt();
    }

    public void addWorkloadExpertise(WorkloadExpertise workloadExpertise){
        workloadExpertise.setWorkload(this);
        this.workloadExpertises.add(workloadExpertise);
    }
}
