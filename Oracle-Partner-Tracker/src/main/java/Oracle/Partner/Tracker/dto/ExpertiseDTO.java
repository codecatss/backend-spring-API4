package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.utils.userenum.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpertiseDTO {
    @Schema(description = "Nome da expertise", example = "Expertise XYZ")
    private String name;

    @Schema(description = "Descrição da expertise", example = "A expertise XYZ é essencial para a Track de Cloud Sell")
    private String description;

    @Schema(description = "Quantos meses essa expertise esta valida", example = "24")
    private Integer lifeTimeMonth;

    @Schema(description = "Status da expertise, se esta 'ACTIVE' ou 'INACTIVE'", example = "ACTIVE")
    private Status status;

    public ExpertiseDTO(String name, String description, Integer lifeTimeMonth, String status) {
        if(status.equalsIgnoreCase("ACTIVE")){
            this.status = Status.ACTIVE;
        }
        else if(status.equalsIgnoreCase("INACTIVE")){
            this.status = Status.INACTIVE;
        }
        this.name = name;
        this.description = description;
        this.lifeTimeMonth = lifeTimeMonth;
    }
}
