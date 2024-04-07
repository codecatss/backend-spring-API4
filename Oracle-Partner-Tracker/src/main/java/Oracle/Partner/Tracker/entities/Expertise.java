package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.util.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "service_expertise")
public class Expertise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name", nullable = true, length = 100)
    private String name;

    @Column(name = "description", nullable = true, length = 250)
    private String description;

    @Column(name = "life_time_month", nullable = true)
    private Integer lifeTimeMonth;

    @Column(name = "status")
    private Status status;

    public Expertise(ExpertiseDTO expertiseDTO) {
        this.name = expertiseDTO.getName();
        this.description = expertiseDTO.getDescription();
        this.lifeTimeMonth = expertiseDTO.getLifeTimeMonth();
        this.status = expertiseDTO.getStatus();
    }
}
