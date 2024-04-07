package Oracle.Partner.Tracker.util;

public enum Status {
    ACTIVE,
    INACTIVE;

    public static Status toStatus(String statusString){
        return switch (statusString.toLowerCase().trim()) {
            case "active" -> Status.ACTIVE;
            case "inactive" -> Status.INACTIVE;
            default -> null;
        };
    }
}
