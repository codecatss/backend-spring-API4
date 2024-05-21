package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.repositories.ChangeHistoryRepository;
import Oracle.Partner.Tracker.utils.LocalDateTimeAdapter;
import Oracle.Partner.Tracker.entities.ChangeHistory;
import Oracle.Partner.Tracker.dto.ChangeHistoryDTO;
import Oracle.Partner.Tracker.utils.ChangeType;
import Oracle.Partner.Tracker.utils.Converter;
import Oracle.Partner.Tracker.dto.GenericDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChangeHistoryService {
    @Autowired
    private ChangeHistoryRepository changeHistoryRepository;

    private final Converter converter = new Converter();

    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();

    public List<ChangeHistory> findAll(){
        return changeHistoryRepository.findAll();
    }

    public ResponseEntity saveChangeHistory(Long changedByPartnerId, Long recordId, String tableName, ChangeType changeType, GenericDTO oldEntity, GenericDTO newEntity){
        try{
            ChangeHistoryDTO changeHistoryDTO = new ChangeHistoryDTO(
                    changedByPartnerId,
                    recordId,
                    tableName.toUpperCase(),
                    changeType,
                    this.converter.stringToHexadecimal(entityToJson(oldEntity)),
                    this.converter.stringToHexadecimal(entityToJson(newEntity))
            );
            changeHistoryRepository.save(new ChangeHistory(changeHistoryDTO));
            return ResponseEntity.ok().build();
        }
        catch (Exception error){
            error.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    private String entityToJson(Object entity){
        return gson.toJson(entity);
    }
}

