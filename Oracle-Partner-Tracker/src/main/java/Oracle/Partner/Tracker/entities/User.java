package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.utils.companyEnum.IngestionOperation;
import Oracle.Partner.Tracker.utils.userenum.MembershipEnum;
import Oracle.Partner.Tracker.utils.userenum.RoleEnum;
import Oracle.Partner.Tracker.utils.userenum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private RoleEnum role;
    @Column(name = "status")
    private Status userStatus;
    @Column(name = "ingestion_operation")
    private IngestionOperation ingestionOperation;
    @Column(name = "membership_type")
    private MembershipEnum memberShipType;
    @Column(name = "updated_at")
    private LocalDateTime updateAt;
    @Column(name = "create_at")
    private LocalDateTime createAt;

}
