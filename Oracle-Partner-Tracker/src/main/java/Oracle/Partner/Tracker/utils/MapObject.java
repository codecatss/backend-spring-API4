package Oracle.Partner.Tracker.utils;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapObject {

    static public Map<Integer, Map<String, String>> mapObjectList(List<Object[]> objectList, String[] columnsName, Map<String, String> additionalInformation){
        List<DashboardColorEnum> listOfColorsAlreadyUsed = new ArrayList<>();
        DashboardColorEnum color = DashboardColorEnum.getRandom();
        Map<Integer, Map<String, String>> queryDataMap = new HashMap<>();
        int cont = 1;
        for(Object[] object : objectList){
            Map<String, String> mapOfObject = new HashMap<>();
            for (int i = 0; i < object.length; i++) {
                mapOfObject.put(columnsName[i], object[i] == null ? "null" : object[i].toString());
            }
            if (additionalInformation != null) {
                mapOfObject.putAll(additionalInformation);
            }
            mapOfObject.put("avatarColor", DashboardColorEnum.getValueFormated(DashboardColorEnum.getRandom()));
            queryDataMap.put(cont++, mapOfObject);
            listOfColorsAlreadyUsed.add(color);
            color = DashboardColorEnum.getRandomColorDifferentOf(listOfColorsAlreadyUsed);
        }
        return queryDataMap;
    }
}
