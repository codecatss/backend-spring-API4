package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.dto.CompanyRecord;
import Oracle.Partner.Tracker.entities.relations.CompanyExpertise;
import Oracle.Partner.Tracker.entities.relations.CompanyOpnTrack;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.Status;
import Oracle.Partner.Tracker.utils.OPNStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@EqualsAndHashCode
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList();
    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyOpnTrack> companyOpnTrack = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyExpertise> companyExpertise = new ArrayList<>();

    public Company(){
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public Company(CompanyDTO companyDTO) {
        this();
        this.name = companyDTO.getName();
        this.opnStatus = companyDTO.getOpnStatus();
        this.cnpj = companyDTO.getCnpj();
        this.country = companyDTO.getCountry();
        this.state = companyDTO.getState();
        this.city = companyDTO.getCity();
        this.address = companyDTO.getAddress();
        this.ingestionOperation = companyDTO.getIngestionOperation();
        this.creditHold = companyDTO.getCreditHold();
        this.status = companyDTO.getStatus();
        this.slogan = companyDTO.getSlogan();
    }

    public Company(CompanyRecord companyRecord){
        this();
        this.name = companyRecord.name();
        this.cnpj = companyRecord.cnpj();
        this.city = companyRecord.city();
        this.address = companyRecord.address();
        this.state = companyRecord.state();
        if(!companyRecord.slogan().trim().isEmpty()){
            this.slogan = companyRecord.slogan();
        }
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
