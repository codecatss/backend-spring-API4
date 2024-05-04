package Oracle.Partner.Tracker.services;

import org.springframework.http.ResponseEntity;
import java.util.List;

public interface GenericService {
    void mapCsvToEntities(List<String[]> csvData);
}
