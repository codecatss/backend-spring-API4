package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.entities.OpnTrack;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OpnTrackDTO {

    @Schema(description = "ID da OPN Track", example = "123")
    private Long id;

    @Schema(description = "Nome da OPN Track", example = "CLOUD BUILD")
    private String name;

    @Schema(description = "Status da OPN Track", example = "true")
    private Boolean opnTrackStatus;

    public OpnTrackDTO(OpnTrack entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.opnTrackStatus = entity.getOpnTrackStatus();
    }
    
}
