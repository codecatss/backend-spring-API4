package Oracle.Partner.Tracker.services;

import java.util.List;

public interface GenericService<T> {
    List<T> mapCsvToEntities(List<String[]> csvData);
}
