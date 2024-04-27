package Oracle.Partner.Tracker.entities.relations;

import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.entities.OpnTrack;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "company_opn_tracks")
public class CompanyOpnTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opn_tracks_id")
    private OpnTrack opnTrack;
}