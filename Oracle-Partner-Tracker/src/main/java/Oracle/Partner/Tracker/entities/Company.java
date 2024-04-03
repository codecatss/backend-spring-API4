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

    @Column(name = "name", nullable = true, length = 50)
    private String name;

    @Column(name = "opn_status" )
    private Boolean opnStatus;

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

    @Column(name = "cep", nullable = true, length = 10)
    private String cep;

    @Column(name = "create_on")
    private LocalDateTime createOn;

    @Column(name = "credit_hold")
    private String creditHold;

    @Column(name = "company_status")
    private Boolean companyStatus;

    @Column(name = "slogan", nullable = true, length = 200)
    private String slogan;


}
