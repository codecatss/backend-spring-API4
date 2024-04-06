package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.entities.Expertise;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpertiseDTO {
//    @Schema(description = "ID da expertise", example = "1")
//    private String id;

    @Schema(description = "Nome da expertise", example = "Expertise XYZ")
    private String name;

    @Schema(description = "Descrição da expertise", example = "A expertise XYZ é essencial para a Track de Cloud Sell")
    private String description;

    @Schema(description = "Valor minimo para passar na expertise", example = "70")
    private Integer minScore;

    @Schema(description = "Valor maximo para passar na expertise", example = "100")
    private Integer maxScore;

    @Schema(description = "Quantos meses essa expertise esta valida", example = "24")
    private Integer lifeTimeMonth;
}
