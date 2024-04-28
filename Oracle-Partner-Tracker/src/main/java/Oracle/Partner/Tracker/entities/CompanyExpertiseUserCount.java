package Oracle.Partner.Tracker.entities;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
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

    @Column(name = "progress_percentage")
    private Double progressPercentage;

    @Column(name = "deadline")
    private Integer deadline;

    public CompanyExpertiseUserCount(String companyName, String companyState, String expertiseName, String trackName, Long progressPercentage, Integer deadline) {
        this.companyName = companyName;
        this.companyState = companyState;
        this.expertiseName = expertiseName;
        this.trackName = trackName;
        this.progressPercentage = progressPercentage.doubleValue();
        this.deadline = deadline;
    }
}
