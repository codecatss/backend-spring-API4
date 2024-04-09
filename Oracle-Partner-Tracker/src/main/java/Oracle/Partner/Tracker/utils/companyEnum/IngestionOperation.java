package Oracle.Partner.Tracker.utils.companyEnum;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum IngestionOperation {
    
        CSV("CSV"), MANUAL("MANUAL");

        private String operation;


}
