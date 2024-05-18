package Oracle.Partner.Tracker.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum RoleEnum {

    ADM("ADM"),
    USER("USER");

    @SuppressWarnings("unused")
    private String role;

    public static RoleEnum toRole(String roleString){
        return switch (roleString.toLowerCase().trim()) {
            case "admin" -> RoleEnum.ADM;
            default -> RoleEnum.USER;
        };
    }
}
