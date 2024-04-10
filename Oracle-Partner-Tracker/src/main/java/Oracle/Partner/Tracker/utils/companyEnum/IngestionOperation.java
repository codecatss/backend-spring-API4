package Oracle.Partner.Tracker.utils.companyEnum;

import Oracle.Partner.Tracker.utils.userenum.MembershipEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum IngestionOperation {
    
        CSV("CSV"), MANUAL("MANUAL");

        private String operation;

        public static IngestionOperation toIngestionOperation(String operation){
                return switch (operation.toLowerCase().trim()) {
                        case "manual" -> IngestionOperation.MANUAL;
                        case "csv" -> IngestionOperation.CSV;
                        default -> null;
                };
        }

}
