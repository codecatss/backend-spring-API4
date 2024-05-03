package Oracle.Partner.Tracker.entities;

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
@EqualsAndHashCode
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "opn_status" )
    private OPNStatus opnStatus;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name = "country")
    private String country;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "address")
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
    @Column(name = "slogan")
    private String slogan;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList();
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyOpnTrack> companyOpnTrack = new ArrayList<>();

    public void addCompanyOpnTrack(CompanyOpnTrack companyOpnTrack){
        companyOpnTrack.setCompany(this);
        this.companyOpnTrack.add(companyOpnTrack);
    }
}
