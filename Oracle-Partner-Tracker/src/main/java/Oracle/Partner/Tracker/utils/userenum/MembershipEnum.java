package Oracle.Partner.Tracker.utils.userenum;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum MembershipEnum {

    PRINCIPAL("PRINCIPAL"), WORLDWIDE("WORLDWIDE");

    private String membership;

    public static MembershipEnum toMembership(String membershipString){
        return switch (membershipString.toLowerCase().trim()) {
            case "principal" -> MembershipEnum.PRINCIPAL;
            case "worldwide" -> MembershipEnum.WORLDWIDE;
            default -> null;
        };
    }
}
