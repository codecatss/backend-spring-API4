package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.utils.companyEnum.IngestionOperation;
import Oracle.Partner.Tracker.utils.userenum.MembershipEnum;
import Oracle.Partner.Tracker.utils.userenum.RoleEnum;
import Oracle.Partner.Tracker.utils.userenum.Status;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserDTO (
        @Schema(description = "Nome do usuário", example = "João da Silva")
        String name,
        @Schema(description = "E-mail do usuário", example = "abcd@fatec.com.br")
        String email,
        @Schema(description = "Senha do usuário", example = "123456")
        String password,
        @Schema(description = "Função do usuário", example = "USER")
        RoleEnum role,
        @Schema(description = "Status do usuário", example = "ACTIVE")
        Status status,
        @Schema(description = "Operação de ingestão do usuário", example = "MANUAL")
        IngestionOperation ingestionOperation,
        @Schema(description = "Tipo de associação do usuário", example = "PRINCIPAL")
        MembershipEnum memberShipType
){
}