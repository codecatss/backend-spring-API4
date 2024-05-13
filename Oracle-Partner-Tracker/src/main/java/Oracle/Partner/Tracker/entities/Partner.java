package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.dto.PartnerDTO;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.Status;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "partner")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "ingestion_operation")
    private IngestionOperation ingestionOperation;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();
    @Column(name = "update_at")
    private LocalDateTime updateAt = LocalDateTime.now();

    public Partner() {
        this.ingestionOperation = IngestionOperation.CSV;
        this.status = Status.ACTIVE;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public Partner(PartnerDTO partnerDTO) {
        this.username = partnerDTO.getUsername();
        this.password = partnerDTO.getPassword();
        this.ingestionOperation = partnerDTO.getIngestionOperation();
        this.status = partnerDTO.getStatus();
        this.createAt = partnerDTO.getCreateAt();
        this.updateAt = partnerDTO.getUpdateAt();
    }
}
