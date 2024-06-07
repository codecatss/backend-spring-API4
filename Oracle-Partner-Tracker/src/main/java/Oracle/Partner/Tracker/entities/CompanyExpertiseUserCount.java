package Oracle.Partner.Tracker.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "company_expertise_user_count")
@IdClass(CompanyExpertiseUserCount.CompanyExpertiseUserCountId.class)
public class CompanyExpertiseUserCount implements Serializable {

    @Id
    @Column(name = "company_name")
    private String companyName;

    @Id
    @Column(name = "company_state")
    private String companyState;

    @Id
    @Column(name = "expertise_name")
    private String expertiseName;

    @Id
    @Column(name = "track_name")
    private String trackName;

    @Id
    @Column(name = "workload_name")
    private String workloadName;

    @Column(name = "total_certifications")
    private Long totalCertifications;

    @Column(name = "passed_certifications")
    private Long passedCertifications;

    @Column(name = "completion_percentage")
    private Double completionPercentage;

    public CompanyExpertiseUserCount(String companyName, String companyState, String expertiseName, String trackName, String workloadName, Long totalCertifications, Long passedCertifications, Double completionPercentage) {
        this.companyName = companyName;
        this.companyState = companyState;
        this.expertiseName = expertiseName;
        this.trackName = trackName;
        this.workloadName = workloadName;
        this.totalCertifications = totalCertifications;
        this.passedCertifications = passedCertifications;
        this.completionPercentage = completionPercentage;
    }

    @Data
    public static class CompanyExpertiseUserCountId implements Serializable {
        private String companyName;
        private String companyState;
        private String expertiseName;
        private String trackName;
        private String workloadName;
    }
}
