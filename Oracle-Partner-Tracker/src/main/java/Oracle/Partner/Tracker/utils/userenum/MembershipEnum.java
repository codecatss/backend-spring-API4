package Oracle.Partner.Tracker.utils.userenum;

public enum MembershipEnum {

    PRINCIPAL("PRINCIPAL"), WORLDWIDE("WORLDWIDE");

    private String membership;


    MembershipEnum(String S) {
        this.membership = S;
    }
}