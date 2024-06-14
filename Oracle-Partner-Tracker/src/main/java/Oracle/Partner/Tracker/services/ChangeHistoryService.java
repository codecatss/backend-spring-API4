package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.entities.Partner;
import Oracle.Partner.Tracker.repositories.ChangeHistoryRepository;
import Oracle.Partner.Tracker.utils.LocalDateTimeAdapter;
import Oracle.Partner.Tracker.entities.ChangeHistory;
import Oracle.Partner.Tracker.dto.ChangeHistoryDTO;
import Oracle.Partner.Tracker.utils.ChangeType;
import Oracle.Partner.Tracker.dto.GenericDTO;

import Oracle.Partner.Tracker.utils.MapObjectList;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;




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
                    "",
                    getDifferenceBetweenJSON(entityToJson(oldEntity), entityToJson(newEntity))
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

    private String getDifferenceBetweenJSON(String oldEntity, String newEntity) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return findJsonDifferences(mapper.readTree(oldEntity), mapper.readTree(newEntity)).toString();
    }

    private static JsonNode findJsonDifferences(JsonNode node1, JsonNode node2) {
        if (node1.equals(node2)) {
            return null;
        }

        if (node1.isObject() && node2.isObject()) {
            ObjectNode diff = ((ObjectNode) node2.deepCopy());
            node1.fields().forEachRemaining(entry -> {
                String key = entry.getKey();
                JsonNode value1 = entry.getValue();
                JsonNode value2 = node2.get(key);
                JsonNode childDiff = findJsonDifferences(value1, value2);
                if (childDiff != null) {
                    diff.set(key, childDiff);
                } else {
                    diff.remove(key);
                }
            });
            return diff.isEmpty() ? null : diff;
        } else {
            return node2;
        }
    }
}

