package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.utils.ChangeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeHistoryDTO {
    @Schema(description = "Id do Partner que mudou a tabela")
    private Long changedByPartnerId;
    @Schema(description = "Nome da tabela que foi alterada")
    private String tableName;
    @Schema(description = "Tipo de mudança")
    private ChangeType changeType;
    @Schema(description = "Valor antigo")
    private String oldValueHexadecimal;
    @Schema(description = "Novo valor")
    private String newValueHexadecimal;
    @Schema(description = "Data de mudança")
    private LocalDateTime changedAt;

    public ChangeHistoryDTO(Long changedByPartnerId, String tableName, ChangeType changeType, String oldValueJsonFormat, String newValueJsonFormat) {
        this.changedByPartnerId = changedByPartnerId;
        this.tableName = tableName;
        this.changeType = changeType;
        this.oldValueHexadecimal = oldValueJsonFormat;
        this.newValueHexadecimal = newValueJsonFormat;
        this.changedAt = LocalDateTime.now();
    }
}
