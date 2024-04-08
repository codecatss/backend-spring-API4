package Oracle.Partner.Tracker.utils.userenum;

public enum RoleEnum {

    ADM("ADMIN"),
    USER("USER");

    private String role;

    public static RoleEnum toRole(String roleString){
        return switch (roleString.toLowerCase().trim()) {
            case "admin" -> RoleEnum.ADM;
            case "user" -> RoleEnum.USER;
            default -> null;
        };
    }

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
