package Oracle.Partner.Tracker.utils;


public enum CertificationStatus {
    PASSED, 
    IN_PROGRESS, 
    EXPIRED;

    public static CertificationStatus toStatus(String statusString){
        return switch (statusString.toLowerCase().trim()) {
            case "passed" -> CertificationStatus.PASSED;
            case "in_progress" -> CertificationStatus.IN_PROGRESS;
            default -> EXPIRED;
        };
    }

}
