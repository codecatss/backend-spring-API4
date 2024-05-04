package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.repositories.CompanyRepository;
import Oracle.Partner.Tracker.repositories.UserRepository;
import com.opencsv.bean.CsvBindByName;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.MembershipEnum;
import Oracle.Partner.Tracker.utils.RoleEnum;
import Oracle.Partner.Tracker.utils.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDTO implements GenericDTO{

    @Autowired
    private CompanyRepository companyRepository;


    @CsvBindByName(column = "User OPN Admin Name")
    @Schema(description = "Nome do usuário", example = "João da Silva")
    String name;

    @CsvBindByName(column = "User OPN Admin Email")
    @Schema(description = "E-mail do usuário", example = "abcd@fatec.com.br")
    String email;

    @Schema(description = "Senha do usuário", example = "123456")
    String password;

    @CsvBindByName(column = "User OPN Admin Role")
    private String roleStatusString;

    @Schema(description = "Função do usuário", example = "USER")
    RoleEnum role;

    @CsvBindByName(column = "User OPN Admin Status")
    private String statusString;

    @Schema(description = "Status do usuário", example = "ACTIVE")
    Status status;

    @Schema(description = "Data de criação do usuário", example = "2022-01-01T12:00:00")
    private LocalDateTime createAt;

    @Schema(description = "Data de atualização do usuário", example = "2022-01-01T12:00:00")
    private LocalDateTime updateAt;

    @Schema(description = "Operação de ingestão do usuário", example = "MANUAL")
    IngestionOperation ingestionOperation;

    @CsvBindByName(column = "User Membership Type")
    String memberShipTypeString;

    @Schema(description = "Tipo de associação do usuário", example = "PRINCIPAL")
    MembershipEnum memberShipType;

    @CsvBindByName(column = "Company CNPJ")
    private String cnpjComanyString;

    @Schema(description = "Company que o usuario esta", example = "Company ABC")
    private Company company;

    public UserDTO() {
        this.password = "oracle";
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.ingestionOperation = IngestionOperation.CSV;
    }

    public void setStatusString(String statusString){
        this.status = Status.toStatus(statusString);
    }

    public void setRoleStatusString(String roleStatusString){
        this.role = RoleEnum.toRole(roleStatusString);
    }

    public void setMemberShipTypeString(String memberShipTypeString){
        this.memberShipType = MembershipEnum.toMembership(memberShipTypeString);
    }

    public void setCnpjComanyString(String cnpjComanyString){
        this.company = companyRepository.findByCnpj(cnpjComanyString);
    }

}

