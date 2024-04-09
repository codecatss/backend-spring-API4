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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status userStatus;
    @Column(name = "ingestion_operation")
    @Enumerated(EnumType.STRING)
    private IngestionOperation ingestionOperation;
    @Column(name = "membership_type")
    @Enumerated(EnumType.STRING)
    private MembershipEnum memberShipType;
    @Column(name = "updated_at")
    private LocalDateTime updateAt = LocalDateTime.now();
    @Column(name = "created_at")
    private LocalDateTime createAt = LocalDateTime.now();

}
