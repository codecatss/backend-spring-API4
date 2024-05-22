package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.dto.ChangeHistoryDTO;
import Oracle.Partner.Tracker.utils.ChangeType;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "change_history")
public class ChangeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "changed_by_partner_id")
    private Partner changedByPartnerId;
    @Column(name = "record_id")
    private Long recordId;
    @Column(name = "table_name")
    private String tableName;
    @Column(name = "change_type")
    @Enumerated(EnumType.STRING)
    private ChangeType changeType;
    @Column(name = "old_value_hexadecimal")
    private String oldValueHexadecimal;
    @Column(name = "new_value_hexadecimal")
    private String newValueHexadecimal;
    @Column(name = "changed_at")
    private LocalDateTime changedAt;

    public ChangeHistory(ChangeHistoryDTO changeHistoryDTO) {
        this.changedByPartnerId = changeHistoryDTO.getChangedByPartnerId();
        this.recordId = changeHistoryDTO.getRecordId();
        this.tableName = changeHistoryDTO.getTableName();
        this.changeType = changeHistoryDTO.getChangeType();
        this.oldValueHexadecimal = changeHistoryDTO.getOldValueHexadecimal();
        this.newValueHexadecimal = changeHistoryDTO.getNewValueHexadecimal();
        this.changedAt = changeHistoryDTO.getChangedAt();
    }
}
