package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.utils.companyEnum.CompanyStatus;
import Oracle.Partner.Tracker.utils.companyEnum.IngestionOperation;
import Oracle.Partner.Tracker.utils.companyEnum.OPNStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = true, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "opn_status" )
    private OPNStatus opnStatus;

    @Column(name = "cnpj", unique = false, nullable = true, length = 150)
    private String cnpj;

    @Column(name = "country", nullable = true, length = 20)
    private String country;

    @Column(name = "state", nullable = true, length = 50)
    private String state;

    @Column(name = "city", nullable = true, length = 100)
    private String city;

    @Column(name = "address", nullable = true, length = 200)
    private String address;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name= "ingestion_operation")
    @Enumerated(EnumType.STRING)
    private IngestionOperation ingestionOperation;

    @Column(name = "credit_hold")
    private String creditHold;

    @Column(name = "company_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CompanyStatus companyStatus;

    @Column(name = "slogan", nullable = true, length = 200)
    private String slogan;


}
