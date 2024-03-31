package Oracle.Partner.Tracker.entities;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "opn_status" )
    private Boolean opnStatus;

    @Column(name = "cnpj", unique = true, nullable = false, length = 150)
    private String cnpj;

    @Column(name = "country", nullable = false, length = 20)
    private String country;

    @Column(name = "state", nullable = false, length = 50)
    private String state;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "cep", nullable = false, length = 10)
    private String cep;

    @Column(name = "create_on")
    private LocalDateTime createOn;

    @Column(name = "credit_hold")
    private Boolean creditHold;

    @Column(name = "company_status")
    private Boolean companyStatus;

    @Column(name = "slogan", nullable = false, length = 200)
    private String slogan;


}
