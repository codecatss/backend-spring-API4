package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.entities.Workload;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkloadDTO {

    @Schema(description = "ID do Workload", example = "1")
    private String id;

    @Schema(description = "Nome do Workload", example = "AI infrastructure")
    private String name;

    @Schema(description = "Descrição do workload", example = "AI infrastructure 2")
    private String description;

    @Schema(description = "Data de criação do Workload", example = "2022-01-01T12:00:00")
    private LocalDateTime created_on;

    // Construtor que converte uma entidade Workload para um objeto WorkloadDTO
    public WorkloadDTO(Workload entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.created_on = entity.getCreated_On();
    }
}
