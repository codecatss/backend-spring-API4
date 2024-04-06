package Oracle.Partner.Tracker.entities;

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
}
