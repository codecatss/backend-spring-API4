package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.dto.ExpertiseDTO;
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
@Table(name = "service_expertise")
public class Expertise {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = true, length = 100)
    private String name;

    @Column(name = "description", nullable = true, length = 250)
    private String description;

    @Column(name = "min_score", nullable = true)
    private Integer minScore;

    @Column(name = "max_score", nullable = true)
    private Integer maxScore;

    @Column(name = "life_time_month", nullable = true)
    private Integer lifeTimeMonth;

    public Expertise(ExpertiseDTO expertiseDTO) {
        this.id = expertiseDTO.getId();
        this.name = expertiseDTO.getName();
        this.description = expertiseDTO.getDescription();
        this.minScore = expertiseDTO.getMinScore();
        this.maxScore = expertiseDTO.getMaxScore();
        this.lifeTimeMonth = expertiseDTO.getLifeTimeMonth();
    }
}
