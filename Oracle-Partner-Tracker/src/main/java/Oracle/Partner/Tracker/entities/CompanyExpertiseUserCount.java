package Oracle.Partner.Tracker.entities;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity(name = "company_expertise_user_count")
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

    public CompanyExpertiseUserCount(String companyName, String companyState, String expertiseName, String trackName, Long totalCertifications, Long passedCertifications, Double completionPercentage) {
        this.companyName = companyName;
        this.companyState = companyState;
        this.expertiseName = expertiseName;
        this.trackName = trackName;
        this.totalCertifications = totalCertifications;
        this.passedCertifications = passedCertifications;
        this.completionPercentage = completionPercentage;
    }
}