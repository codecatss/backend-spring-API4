package Oracle.Partner.Tracker.utils.userenum;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


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
