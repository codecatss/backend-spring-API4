package Oracle.Partner.Tracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record UserDTO (
        @Schema(description = "ID do usuário", example = "1")
        String id,
        @Schema(description = "Nome do usuário", example = "João da Silva")
        String name,
        @Schema(description = "E-mail do usuário", example = "abcd@oracle.com.br")
        String email,
        @Schema(description = "Senha do usuário", example = "123456")
        String password,
        @Schema(description = "Função do usuário", example = "Admin")
        String role,
        @Schema(description = "Status do usuário", example = "true")
        Boolean userStatus,
        @Schema(description = "Tipo de associação do usuário", example = "Gold")
        String memberShipType,
        @Schema(description = "Data de criação do usuário", example = "2024-04-01T12:00:00")
        LocalDateTime createOn
){
}
