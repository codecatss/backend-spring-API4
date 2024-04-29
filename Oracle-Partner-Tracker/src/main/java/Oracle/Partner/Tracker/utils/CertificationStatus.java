package Oracle.Partner.Tracker.utils;

public enum CertificationStatus {
    PASSED("PASSED"), 
    IN_PROGRESS("IN_PROGRESS"), 
    EXPIRED("EXPIRED");

    private final String statusText;

    CertificationStatus(String statusText) {
        this.statusText = statusText;
    }

    public String getStatusText() {
        return statusText;
    }
    public static CertificationStatus toStatus(String statusString) {
        return switch (statusString.toUpperCase().trim()) {
            case "PASSED" -> CertificationStatus.PASSED;
            case "IN_PROGRESS" -> CertificationStatus.IN_PROGRESS;
            case "EXPIRED" -> CertificationStatus.EXPIRED;
            default -> null;
        };
    }
}
