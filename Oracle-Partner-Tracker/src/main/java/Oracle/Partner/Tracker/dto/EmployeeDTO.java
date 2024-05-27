package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.utils.RoleEnum;
import com.opencsv.bean.CsvBindByName;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.MembershipEnum;
import Oracle.Partner.Tracker.utils.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EmployeeDTO implements GenericDTO{

    @CsvBindByName(column = "User OPN Admin Name")
    @Schema(description = "Nome do usuário", example = "João da Silva")
    private String name;

    @CsvBindByName(column = "User OPN Admin Email")
    @Schema(description = "E-mail do usuário", example = "abcd@fatec.com.br")
    private String email;

    @Schema(description = "Senha do usuário", example = "123456")
    private String password;

    @CsvBindByName(column = "User OPN Admin Status")
    private String statusString;

    @Schema(description = "Status do usuário", example = "ACTIVE")
    private Status status;

    @Schema(description = "Data de criação do usuário", example = "2022-01-01T12:00:00")
    private LocalDateTime createAt;

    @Schema(description = "Data de atualização do usuário", example = "2022-01-01T12:00:00")
    private LocalDateTime updateAt;

    @Schema(description = "Operação de ingestão do usuário", example = "MANUAL")
    private IngestionOperation ingestionOperation;

    @CsvBindByName(column = "User Membership Type")
    private String memberShipTypeString;

    @Schema(description = "Tipo de associação do usuário", example = "PRINCIPAL")
    private MembershipEnum memberShipType;

    @CsvBindByName(column = "Company CNPJ")
    private String cnpjCompanyString;

    @Schema(description = "Company que o usuario esta", example = "Company ABC")
    private Company company;

    public EmployeeDTO() {
        this.password = "oracle";
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.status = Status.ACTIVE;
        this.ingestionOperation = IngestionOperation.CSV;
    }

    public EmployeeDTO(String name, String email, String cnpj, String password) {
        this.name = name;
        this.email = email;
        this.cnpjCompanyString = cnpj;
        this.password = password;
        this.memberShipType = MembershipEnum.PRINCIPAL;
    }

    public void setStatusString(String statusString){
        this.status = Status.toStatus(statusString);
    }

    public void setMemberShipTypeString(String memberShipTypeString){
        this.memberShipType = MembershipEnum.toMembership(memberShipTypeString);
    }
}

