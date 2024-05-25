package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.dto.ExpertiseRecord;
import Oracle.Partner.Tracker.entities.relations.ExpertiseCertification;
import Oracle.Partner.Tracker.entities.relations.OpnTrackExpertise;
import Oracle.Partner.Tracker.entities.relations.WorkloadExpertise;
import Oracle.Partner.Tracker.entities.relations.CompanyExpertise;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.utils.Status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

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
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<OpnTrackExpertise> opnTrackExpertise = new ArrayList<>();
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<WorkloadExpertise> workloadExpertise = new ArrayList<>();
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<ExpertiseCertification> expertiseCertification = new ArrayList<>();
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    @Enumerated(EnumType.STRING)
    @Column(name= "ingestion_operation")
    private IngestionOperation ingestionOperation;
//    @OneToMany(mappedBy = "expertise", cascade = CascadeType.ALL)
//    private List<CompanyExpertise> companyExpertise = new ArrayList<>();

    public void addCompanyExpertise(CompanyExpertise companyExpertise){
//        companyExpertise.setExpertise(this);
//        this.companyExpertise.add(companyExpertise);
    }

    public Expertise(ExpertiseDTO expertiseDTO) {
        this.name = expertiseDTO.getName();
        this.description = expertiseDTO.getDescription();
        this.status = expertiseDTO.getStatus();
        this.createAt = expertiseDTO.getCreateAt();
        this.updateAt = expertiseDTO.getUpdateAt();
        this.ingestionOperation = expertiseDTO.getIngestionOperation();
    }

    public Expertise(ExpertiseRecord expertiseRecord){
        this.name = expertiseRecord.name();
        this.description = expertiseRecord.description();
        this.status = Status.valueOf(expertiseRecord.statusString());
    }

    public void addWorkloadExpertise(WorkloadExpertise workloadExpertise){
        workloadExpertise.setExpertise(this);
//        this.workloadExpertise.add(workloadExpertise);
    }

    public void addOpnTracksExpertise(OpnTrackExpertise opnTrackExpertise){
        opnTrackExpertise.setExpertise(this);
//        this.opnTrackExpertise.add(opnTrackExpertise);
    }

    public void addExpertiseCertification(ExpertiseCertification expertiseCertification){
        expertiseCertification.setExpertise(this);
//        this.expertiseCertification.add(expertiseCertification);
    }
}
