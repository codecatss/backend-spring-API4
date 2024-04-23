package Oracle.Partner.Tracker.utils;

import java.util.Random;

public enum DashboardColorEnum {
    SUCCESS,
    PRIMARY,
    SECONDARY,
    INFO,
    WARNING,
    ERROR;

    public static DashboardColorEnum getRandom(){
        DashboardColorEnum[] avatarColors = DashboardColorEnum.values();
        int num = new Random().nextInt(avatarColors.length);
        return avatarColors[num];
    }
}
