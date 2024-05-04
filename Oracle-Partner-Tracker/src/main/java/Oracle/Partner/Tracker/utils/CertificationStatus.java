package Oracle.Partner.Tracker.utils;

public enum CertificationStatus {
    PASSED, 
    IN_PROGRESS, 
    EXPIRED;

    public static CertificationStatus toStatus(String statusString) {
        statusString = statusString.trim().toUpperCase();
        try {
            return CertificationStatus.valueOf(statusString);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}