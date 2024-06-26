package Oracle.Partner.Tracker.dto;

import java.time.LocalDateTime;

import com.opencsv.bean.CsvBindByName;
import Oracle.Partner.Tracker.entities.OpnTrack;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OpnTrackDTO implements GenericDTO{

    @Schema(description = "ID da OPN Track", example = "123")
    private Long id;

    @CsvBindByName(column = "OPN Track Name")
    @Schema(description = "Nome da OPN Track", example = "CLOUD BUILD")
    private String name;

    @Schema(description = "Operação de ingestão da OPN Track", example = "CSV")
    private IngestionOperation ingestionOperation;

    @CsvBindByName(column = "OPN Track Status")
    private String statusString;

    @Schema(description = "Status da OPN Track", example = "true")
    private Status status;

    @Schema(description = "Data de criação da OpnTrack", example = "2022-01-01T12:00:00")
    private LocalDateTime createAt;

    @Schema(description = "Data de atualização da OpnTrack", example = "2022-01-01T12:00:00")
    private LocalDateTime updateAt;

    public OpnTrackDTO() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.status = Status.ACTIVE;
        this.ingestionOperation = IngestionOperation.CSV;
    }

    public OpnTrackDTO(OpnTrack entity){
        this();
        this.id = entity.getId();
        this.name = entity.getName();
        this.ingestionOperation = entity.getIngestionOperation();
        this.status = entity.getStatus();
    }

    public OpnTrackDTO(String name, String statusString) {
        this();
        this.name = name;
        this.status = Status.toStatus(statusString);
        this.ingestionOperation = IngestionOperation.MANUAL;
    }

    public void setStatusString(String statusString){
        this.status = Status.toStatus(statusString);
    }

}
