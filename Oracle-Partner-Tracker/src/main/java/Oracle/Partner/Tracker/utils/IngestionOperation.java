package Oracle.Partner.Tracker.utils;

public enum IngestionOperation {
    CSV,
    MANUAL;

    public static IngestionOperation toIngestionOperation(String ingestionOperationString){
        return switch (ingestionOperationString.toLowerCase().trim()) {
//            case "active" -> Status.ACTIVE;
            case "csv" -> IngestionOperation.CSV;
            default -> IngestionOperation.MANUAL;
        };
    }
}
