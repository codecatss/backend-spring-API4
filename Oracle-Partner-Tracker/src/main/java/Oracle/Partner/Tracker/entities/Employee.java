package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.dto.EmployeeDTO;
import Oracle.Partner.Tracker.entities.relations.EmployeeCertification;
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
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    @Enumerated(EnumType.STRING)
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
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeCertification> employeeCertification = new ArrayList<>();

    public void addUserCertificartion(EmployeeCertification userCertificartion){
        userCertificartion.setEmployee(this);
        this.employeeCertification.add(userCertificartion);
    }

    public Employee(EmployeeDTO employeeDTO) {
        this.company = employeeDTO.getCompany();
        this.name = employeeDTO.getName();
        this.email = employeeDTO.getEmail();
        this.password = employeeDTO.getPassword();
        this.role = employeeDTO.getRole();
        this.ingestionOperation = employeeDTO.getIngestionOperation();
        this.status = employeeDTO.getStatus();
        this.memberShipType = employeeDTO.getMemberShipTypeString();
        this.createAt = employeeDTO.getCreateAt();
        this.updateAt = employeeDTO.getUpdateAt();
    }
}
