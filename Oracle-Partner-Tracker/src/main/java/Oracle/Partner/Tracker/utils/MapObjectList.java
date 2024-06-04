package Oracle.Partner.Tracker.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapObjectList {
    public static Map<Integer, Map<String, String>> mapObjectList(List<Object[]> objectList, String[] columns, String dateFormat) {
        Map<Integer, Map<String, String>> resultMap = new HashMap<>();

        for (int i = 0; i < objectList.size(); i++) {
            Object[] object = objectList.get(i);
            Map<String, String> columnMap = new HashMap<>();
            for (int j = 0; j < columns.length; j++) {
                if (object[j] != null) {
                    columnMap.put(columns[j], object[j].toString());
                } else {
                    columnMap.put(columns[j], null);
                }
            }
            resultMap.put(i, columnMap);
        }

        return resultMap;
    }
}
