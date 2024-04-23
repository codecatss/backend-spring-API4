package Oracle.Partner.Tracker.dto;

import java.time.LocalDateTime;
import Oracle.Partner.Tracker.entities.OpnTrack;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OpnTrackDTO {

    @Schema(description = "ID da OPN Track", example = "123")
    private Long id;

    @Schema(description = "Nome da OPN Track", example = "CLOUD BUILD")
    private String name;

    @Schema(description = "Operação de ingestão da OPN Track", example = "CSV")
    private IngestionOperation ingestionOperation;

    @Schema(description = "Status da OPN Track", example = "true")
    private Status status;

    @Schema(description = "Data de criação da OpnTrack", example = "2022-01-01T12:00:00")
    private LocalDateTime createAt;

    @Schema(description = "Data de atualização da OpnTrack", example = "2022-01-01T12:00:00")
    private LocalDateTime updateAt;

    public OpnTrackDTO(OpnTrack entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.ingestionOperation = entity.getIngestionOperation();
        this.status = entity.getStatus();
        this.createAt = entity.getCreateAt();
        this.updateAt = entity.getUpdateAt();
    }
    
}
