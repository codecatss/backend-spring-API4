package Oracle.Partner.Tracker.utils.userenum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum RoleEnum {

    ADM("ADM"),
    USER("USER");

    private String role;

    public static RoleEnum toRole(String roleString){
        return switch (roleString.toLowerCase().trim()) {
            case "admin" -> RoleEnum.ADM;
            case "user" -> RoleEnum.USER;
            default -> null;
        };
    }
}
