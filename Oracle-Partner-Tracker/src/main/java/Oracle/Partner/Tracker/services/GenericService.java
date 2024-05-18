package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.GenericDTO;
import java.util.List;

public interface GenericService {
    Class<?> getDtoClass();
    void saveAllGenericDTO(List<GenericDTO> genericDTOList);
}
