package Oracle.Partner.Tracker.dto;

import com.opencsv.bean.CsvBindByName;
import Oracle.Partner.Tracker.entities.Workload;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import Oracle.Partner.Tracker.utils.Status;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WorkloadDTO implements GenericDTO{

    @Schema(description = "ID do Workload", example = "1")
    private Long id;

    @CsvBindByName(column = "Workload Name")
    @Schema(description = "Nome do Workload", example = "AI infrastructure")
    private String name;

    @CsvBindByName(column = "Workload Description")
    @Schema(description = "Descrição do workload", example = "AI infrastructure 2")
    private String description;

    @CsvBindByName(column = "Workload Status")
    String statusString;

    @Schema(description = "Status do Workload", example = "true")
    private Status status;

    @Schema(description = "Data de criação do workload", example = "2022-01-01T12:00:00")
    private LocalDateTime createAt;

    @Schema(description = "Data de atualização do workload", example = "2022-01-01T12:00:00")
    private LocalDateTime updateAt;

    @Schema(description = "Operação de ingestão do workload", example = "MANUAL")
    private IngestionOperation ingestionOperation;

    public WorkloadDTO() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.ingestionOperation = IngestionOperation.CSV;
    }

    public WorkloadDTO(Workload entity){
        this();
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.status = entity.getStatus();
        this.ingestionOperation = entity.getIngestionOperation();
//        this.createAt = entity.getCreateAt();
//        this.updateAt = entity.getUpdateAt();
    }

    public WorkloadDTO(String name, String description, String statusString) {
        this();
        this.name = name;
        this.description = description;
        this.status = Status.toStatus(statusString);
        this.ingestionOperation = IngestionOperation.MANUAL;
    }

    public void setStatusString(String statusString){
        this.status = Status.toStatus(statusString);
    }

}
