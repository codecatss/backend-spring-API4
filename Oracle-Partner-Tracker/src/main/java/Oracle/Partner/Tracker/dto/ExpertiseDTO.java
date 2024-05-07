package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.utils.IngestionOperation;
import com.opencsv.bean.CsvBindByName;
import Oracle.Partner.Tracker.utils.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExpertiseDTO implements GenericDTO{
    @CsvBindByName(column = "Expertise Service Name")
    @Schema(description = "Nome da expertise", example = "Expertise XYZ")
    private String name;

    @CsvBindByName(column = "Expertise Service Description")
    @Schema(description = "Descrição da expertise", example = "A expertise XYZ é essencial para a Track de Cloud Sell")
    private String description;

    @CsvBindByName(column = "Expertise Service Status")
    private String statusString;

    @Schema(description = "Status da expertise, se esta 'ACTIVE' ou 'INACTIVE'", example = "ACTIVE")
    private Status status;

    @Schema(description = "Data de criação da expertise", example = "2022-01-01T12:00:00")
    private LocalDateTime createAt;

    @Schema(description = "Data de atualização da expertise", example = "2022-01-01T12:00:00")
    private LocalDateTime updateAt;

    @Schema(description = "Operação de ingestão do expertise", example = "MANUAL")
    private IngestionOperation ingestionOperation;

    public ExpertiseDTO() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.status = Status.ACTIVE;
        this.ingestionOperation = IngestionOperation.CSV;
    }

    public ExpertiseDTO(String name, String description, String statusString) {
        this();
        this.name = name;
        this.description = description;
        this.statusString = statusString;
        this.ingestionOperation = IngestionOperation.MANUAL;
    }

    public void setStatusString(String statusString){
        this.status = Status.toStatus(statusString);
    }

}
