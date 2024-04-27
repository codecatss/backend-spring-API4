package Oracle.Partner.Tracker.utils;

import java.util.List;
import java.util.Random;

public enum DashboardColorEnum {
    FFEBCD, BC8F8F, D2691E, C71585, F08080, AFEEEE, B0E0E6, FF6347, B8860B, FA8072,
    BA55D3, BDB76B, FFA07A, FAFAD2, F5DEB3, F0FFFF, E6E6FA, D8BFD8, FF00FF, FF0000;

    public static String getValueFormated(DashboardColorEnum color){
        return "#"+color.toString();
    }

    public static DashboardColorEnum getRandom(){
        DashboardColorEnum[] avatarColors = DashboardColorEnum.values();
        int num = new Random().nextInt(avatarColors.length);
        return avatarColors[num];
    }

    public static DashboardColorEnum getRandomColorDifferentOf(List<DashboardColorEnum> listOfColorsAlreadyUsed){
        DashboardColorEnum[] avatarColors = DashboardColorEnum.values();
        for (int i = 0; i < avatarColors.length; i++) {
            if(!listOfColorsAlreadyUsed.contains(avatarColors[i])){
                return avatarColors[i];
            }
        }
        return getRandom();
    }
}
