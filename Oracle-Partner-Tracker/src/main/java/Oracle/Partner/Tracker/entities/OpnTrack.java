package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.entities.relations.CompanyOpnTrack;
import Oracle.Partner.Tracker.entities.relations.OpnTrackExpertise;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Oracle.Partner.Tracker.utils.Status;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "opn_track")
public class OpnTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "ingestion_operation", nullable = false)
    @Enumerated(EnumType.STRING)
    private IngestionOperation ingestionOperation;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    @OneToMany(mappedBy = "opnTrack")
    private List<CompanyOpnTrack> companyOpnTrack = new ArrayList<>();
    @OneToMany(mappedBy = "opnTrack")
    private List<OpnTrackExpertise> opnTrackExpertise = new ArrayList<>();

    public void addOpnTracksExpertise(OpnTrackExpertise opnTrackExpertise){
        opnTrackExpertise.setOpnTrack(this);
        this.opnTrackExpertise.add(opnTrackExpertise);
    }
    public void addCompanyOpnTrack(CompanyOpnTrack companyOpnTrack){
        companyOpnTrack.setOpnTrack(this);
        this.companyOpnTrack.add(companyOpnTrack);
    }
}