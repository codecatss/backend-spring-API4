package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.dto.UserDTO;
import Oracle.Partner.Tracker.entities.relations.UserCertification;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.RoleEnum;
import Oracle.Partner.Tracker.utils.Status;
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
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    private String name;
    private String email;
    private String password;
    private RoleEnum role;
    @Column(name = "ingestion_operation")
    private IngestionOperation ingestionOperation;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "membership_type")
    private String memberShipType;
    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();
    @Column(name = "update_at")
    private LocalDateTime updateAt = LocalDateTime.now();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserCertification> userCertification = new ArrayList<>();

    public void addUserCertificartion(UserCertification userCertificartion){
        userCertificartion.setUser(this);
        this.userCertification.add(userCertificartion);
    }

    public User(UserDTO userDTO) {
        this.company = userDTO.getCompany();
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.role = userDTO.getRole();
        this.ingestionOperation = userDTO.getIngestionOperation();
        this.status = userDTO.getStatus();
        this.memberShipType = userDTO.getMemberShipTypeString();
        this.createAt = userDTO.getCreateAt();
        this.updateAt = userDTO.getUpdateAt();
    }
}
