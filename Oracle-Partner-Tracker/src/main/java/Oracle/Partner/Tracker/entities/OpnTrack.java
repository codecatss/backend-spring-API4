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
@Table(name = "opn_track")
public class OpnTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = true, length = 20)
    private String name;

    @Column(name = "opn_track_status", nullable = false, length = 20)
    private Boolean opnTrackStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
