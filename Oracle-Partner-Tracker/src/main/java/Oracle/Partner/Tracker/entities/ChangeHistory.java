package Oracle.Partner.Tracker.entities;

import Oracle.Partner.Tracker.dto.ChangeHistoryDTO;
import Oracle.Partner.Tracker.utils.ChangeType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

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
    @Column(name = "changed_by_partner_id")
    private Long changedByPartnerId;
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
        this.tableName = changeHistoryDTO.getTableName();
        this.changeType = changeHistoryDTO.getChangeType();
        this.oldValueHexadecimal = changeHistoryDTO.getOldValueHexadecimal();
        this.newValueHexadecimal = changeHistoryDTO.getNewValueHexadecimal();
        this.changedAt = changeHistoryDTO.getChangedAt();
    }
}
