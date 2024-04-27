package Oracle.Partner.Tracker.entities.relations;

import Oracle.Partner.Tracker.entities.Certification;
import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.entities.OpnTrack;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "expertise_certification")
public class ExpertiseCertification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certification_id")
    private Certification certification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opn_track_id")
    private Expertise expertise;

}
