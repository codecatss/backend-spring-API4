package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.dto.GenericDTO;
import Oracle.Partner.Tracker.entities.relations.CompanyExpertise;
import Oracle.Partner.Tracker.entities.relations.CompanyOpnTrack;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.Status;
import Oracle.Partner.Tracker.utils.OPNStatus;
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
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "opn_status" )
    private OPNStatus opnStatus;
    private String cnpj;
    private String country;
    private String state;
    private String city;
    private String address;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    @Enumerated(EnumType.STRING)
    @Column(name= "ingestion_operation")
    private IngestionOperation ingestionOperation;
    @Column(name = "credit_hold")
    private String creditHold;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String slogan;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList();
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyOpnTrack> companyOpnTrack = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyExpertise> companyExpertise = new ArrayList<>();

    public Company(CompanyDTO companyDTO) {
        this.name = companyDTO.getName();
        this.opnStatus = companyDTO.getOpnStatus();
        this.cnpj = companyDTO.getCnpj();
        this.country = companyDTO.getCountry();
        this.state = companyDTO.getState();
        this.city = companyDTO.getCity();
        this.address = companyDTO.getAddress();
        this.createAt = companyDTO.getCreateAt();
        this.updateAt = companyDTO.getUpdateAt();
        this.ingestionOperation = companyDTO.getIngestionOperation();
        this.creditHold = companyDTO.getCreditHold();
        this.status = companyDTO.getStatus();
        this.slogan = companyDTO.getSlogan();
    }

    public void addCompanyExpertise(CompanyExpertise companyExpertise){
        companyExpertise.setCompany(this);
        this.companyExpertise.add(companyExpertise);
    }

    public void addCompanyOpnTrack(CompanyOpnTrack companyOpnTrack){
        companyOpnTrack.setCompany(this);
        this.companyOpnTrack.add(companyOpnTrack);
    }
}
