package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.MembershipEnum;
import Oracle.Partner.Tracker.utils.RoleEnum;
import Oracle.Partner.Tracker.utils.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements GenericDTO{
    @Schema(description = "Nome do usuário", example = "João da Silva")
    String name;
    @Schema(description = "E-mail do usuário", example = "abcd@fatec.com.br")
    String email;
    @Schema(description = "Senha do usuário", example = "123456")
    String password;
    @Schema(description = "Função do usuário", example = "USER")
    RoleEnum role;
    @Schema(description = "Status do usuário", example = "ACTIVE")
    Status status;
    @Schema(description = "Operação de ingestão do usuário", example = "MANUAL")
    IngestionOperation ingestionOperation;
    @Schema(description = "Tipo de associação do usuário", example = "PRINCIPAL")
    MembershipEnum memberShipType;
    @Schema(description = "Company que o usuario esta", example = "Company ABC")
    private Company company;

}

