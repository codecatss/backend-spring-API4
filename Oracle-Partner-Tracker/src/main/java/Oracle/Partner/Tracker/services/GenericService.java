package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.GenericDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface GenericService {
    void mapCsvToEntities(List<String[]> csvData);
    Class<?> getDtoClass();
}
