package Oracle.Partner.Tracker.entities.relations;

import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.entities.OpnTrack;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "opn_track_and_expertise")
public class OpnTrackExpertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertise_id")
    private Expertise expertise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opn_track_id")
    private OpnTrack opnTrack;

}