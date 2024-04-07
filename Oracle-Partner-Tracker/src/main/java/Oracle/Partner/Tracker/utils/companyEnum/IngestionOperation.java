package Oracle.Partner.Tracker.utils.companyEnum;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum IngestionOperation {
    
        CSV("Csv"), MANUAL("Manual");

        private String operation;
}
