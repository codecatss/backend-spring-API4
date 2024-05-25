package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.entities.Partner;
import Oracle.Partner.Tracker.repositories.ChangeHistoryRepository;
import Oracle.Partner.Tracker.utils.LocalDateTimeAdapter;
import Oracle.Partner.Tracker.entities.ChangeHistory;
import Oracle.Partner.Tracker.dto.ChangeHistoryDTO;
import Oracle.Partner.Tracker.utils.ChangeType;
import Oracle.Partner.Tracker.dto.GenericDTO;

import Oracle.Partner.Tracker.utils.MapObjectList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ChangeHistoryService {
    @Autowired
    private ChangeHistoryRepository changeHistoryRepository;

    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();

    public List<ChangeHistory> findAll(){
        return changeHistoryRepository.findAll();
    }

    public Map<Integer, Map<String, String>> getAllFromChangeHistoryView(){
        String[] columns = {"changeBy", "changeType","tableName","lineName","oldValue","newValue"};
        return MapObjectList.mapObjectList(changeHistoryRepository.getAllFromChangeHistoryView(), columns, null);
    }

    public Map<Integer, Map<String, String>> getByIdFromChangeHistoryView(String tableName, String recordId){
        String[] columns = {"changeBy","changeType","tableName","lineName","oldValue","newValue","lastChange"};
        return MapObjectList.mapObjectList(changeHistoryRepository.getByIdFromChangeHistoryView(tableName, recordId), columns, null);
    }

    public Map<Integer, Map<String, String>> getAllGroupByFromChangeHistoryView(){
        String[] columns = {"recordId","lineName","tableName","quantity","lastChange"};
        return MapObjectList.mapObjectList(changeHistoryRepository.getAllGroupByFromChangeHistoryView(), columns, null);
    }

    public ResponseEntity saveChangeHistory(Partner changedByPartnerId, Long recordId, String tableName, ChangeType changeType, GenericDTO oldEntity, GenericDTO newEntity){
        try{
            ChangeHistoryDTO changeHistoryDTO = new ChangeHistoryDTO(
                    changedByPartnerId,
                    recordId,
                    tableName.toUpperCase(),
                    changeType,
                    entityToJson(oldEntity),
                    entityToJson(newEntity)
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
