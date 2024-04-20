package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.entities.Workload;
import Oracle.Partner.Tracker.util.IngestionOperation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import Oracle.Partner.Tracker.util.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkloadDTO {

    @Schema(description = "ID do Workload", example = "1")
    private Long id;

    @Schema(description = "Nome do Workload", example = "AI infrastructure")
    private String name;

    @Schema(description = "Descrição do workload", example = "AI infrastructure 2")
    private String description;

    @Schema(description = "Status do Workload", example = "true")
    private Status status;

    @Schema(description = "Data de criação do workload", example = "2022-01-01T12:00:00")
    private LocalDateTime createAt;

    @Schema(description = "Data de atualização do workload", example = "2022-01-01T12:00:00")
    private LocalDateTime updateAt;

    @Schema(name= "ingestion_operation")
    private IngestionOperation ingestionOperation;

    public WorkloadDTO(Workload entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.status = entity.getStatus();
        this.createAt = entity.getCreateAt();
        this.updateAt = entity.getUpdateAt();
        this.ingestionOperation = entity.getIngestionOperation();
    }
}
