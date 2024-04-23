package Oracle.Partner.Tracker.entities;

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
    private String id;
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
    @ManyToMany
    private List<OpnTrack> opnTracks;
}
