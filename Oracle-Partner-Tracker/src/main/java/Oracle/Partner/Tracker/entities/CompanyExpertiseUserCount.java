package Oracle.Partner.Tracker.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company_expertise_user_count")
public class CompanyExpertiseUserCount {

    @Id
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_state")
    private String companyState;

    @Column(name = "expertise_name")
    private String expertiseName;

    @Column(name = "track_name")
    private String trackName;

    @Column(name = "total_certifications")
    private Long totalCertifications;

    @Column(name = "passed_certifications")
    private Long passedCertifications;

    @Column(name = "completion_percentage")
    private Double completionPercentage;



}
