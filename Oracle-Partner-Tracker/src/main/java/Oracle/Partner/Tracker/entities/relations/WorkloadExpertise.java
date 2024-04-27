package Oracle.Partner.Tracker.entities.relations;

import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.entities.Workload;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "workload_and_expertise")
public class WorkloadExpertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertise_id")
    private Expertise expertise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workload_id")
    private Workload workload;
}