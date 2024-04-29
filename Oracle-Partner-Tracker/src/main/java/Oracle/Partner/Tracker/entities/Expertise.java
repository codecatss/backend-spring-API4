package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.entities.relations.CompanyExpertise;
import Oracle.Partner.Tracker.entities.relations.ExpertiseCertification;
import Oracle.Partner.Tracker.entities.relations.OpnTrackExpertise;
import Oracle.Partner.Tracker.entities.relations.WorkloadExpertise;
import Oracle.Partner.Tracker.utils.Status;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "service_expertise")
public class Expertise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(fetch = FetchType.LAZY)
    private List<OpnTrackExpertise> opnTrackExpertise = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY)
    private List<WorkloadExpertise> workloadExpertise = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY)
    private List<ExpertiseCertification> expertiseCertification = new ArrayList<>();
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;


    @OneToMany(mappedBy = "expertise", cascade = CascadeType.ALL)
    private List<CompanyExpertise> companyExpertise = new ArrayList<>();

    public void addCompanyExpertise(CompanyExpertise companyExpertise){
        companyExpertise.setExpertise(this);
        this.companyExpertise.add(companyExpertise);
    }

    public Expertise(ExpertiseDTO expertiseDTO) {
        this.name = expertiseDTO.getName();
        this.description = expertiseDTO.getDescription();
        this.status = expertiseDTO.getStatus();
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public void addWorkloadExpertise(WorkloadExpertise workloadExpertise){
        workloadExpertise.setExpertise(this);
        this.workloadExpertise.add(workloadExpertise);
    }

    public void addOpnTracksExpertise(OpnTrackExpertise opnTrackExpertise){
        opnTrackExpertise.setExpertise(this);
        this.opnTrackExpertise.add(opnTrackExpertise);
    }

    public void addExpertiseCertification(ExpertiseCertification expertiseCertification){
        expertiseCertification.setExpertise(this);
        this.expertiseCertification.add(expertiseCertification);
    }
}
