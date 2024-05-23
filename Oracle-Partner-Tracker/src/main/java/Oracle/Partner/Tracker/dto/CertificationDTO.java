package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.utils.IngestionOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDTO implements GenericDTO {
    
    private String name;

    private String description;

    private IngestionOperation ingestionOperation;

}
