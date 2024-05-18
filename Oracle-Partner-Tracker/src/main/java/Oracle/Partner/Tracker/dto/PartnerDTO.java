package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.RoleEnum;
import Oracle.Partner.Tracker.utils.Status;
import com.opencsv.bean.CsvBindByName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PartnerDTO implements GenericDTO{

    @CsvBindByName(column = "Oracle User Username")
    @Schema(description = "Nome do usuário", example = "João da Silva")
    private String username;

    @CsvBindByName(column = "Oracle User Password")
    @Schema(description = "Senha do usuário", example = "123456")
    private String password;

    @CsvBindByName(column = "Oracle User Status")
    private String statusString;

    @CsvBindByName(column = "Oracle User Role")
    private String roleString;

    @Schema(description = "Operação de ingestão do usuário", example = "MANUAL")
    private IngestionOperation ingestionOperation;

    @Schema(description = "Status do usuário", example = "ACTIVE")
    private Status status;

    @Schema(description = "Role do usuário", example = "USER")
    private RoleEnum role;

    @Schema(description = "Data de criação do usuário", example = "2022-01-01T12:00:00")
    private LocalDateTime createAt;

    @Schema(description = "Data de atualização do usuário", example = "2022-01-01T12:00:00")
    private LocalDateTime updateAt;

    public PartnerDTO() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.status = Status.ACTIVE;
        this.ingestionOperation = IngestionOperation.CSV;
    }

    public PartnerDTO(String username, String password, String role) {
        this();
        this.username = username;
        this.password = password;
        this.role = RoleEnum.valueOf(role);
        this.status = Status.toStatus(statusString);
        this.ingestionOperation = IngestionOperation.MANUAL;
    }

    public void setStatusString(String statusString){
        this.status = Status.toStatus(statusString);
    }

    public void setPassword(String password){
        this.password = password;
    }
}

