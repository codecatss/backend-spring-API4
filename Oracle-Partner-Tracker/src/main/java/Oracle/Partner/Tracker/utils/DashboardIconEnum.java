package Oracle.Partner.Tracker.utils;

public enum DashboardIconEnum {
    BXBARCHARTALT2;

    public static String getIconValue(DashboardIconEnum icon){
        switch (icon){
            case BXBARCHARTALT2 -> {
                return "bx-bar-chart-alt-2";
            }
            default -> {
                return null;
            }
        }
    }
}
