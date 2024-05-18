package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.repositories.ChangeHistoryRepository;
import Oracle.Partner.Tracker.utils.LocalDateTimeAdapter;
import Oracle.Partner.Tracker.entities.ChangeHistory;
import Oracle.Partner.Tracker.dto.ChangeHistoryDTO;
import Oracle.Partner.Tracker.utils.ChangeType;
import Oracle.Partner.Tracker.dto.GenericDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.time.LocalDateTime;

@Service
public class ChangeHistoryService {
    @Autowired
    private ChangeHistoryRepository changeHistoryRepository;

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    public ResponseEntity saveChangeHistory(Long changedByPartnerId, String tableName, ChangeType changeType, GenericDTO oldEntity, GenericDTO newEntity){
        try{
            ChangeHistoryDTO changeHistoryDTO = new ChangeHistoryDTO(
                    changedByPartnerId,
                    tableName.toUpperCase(),
                    changeType,
                    byteToHexadecimal(stringToByte(entityToJson(oldEntity))),
                    byteToHexadecimal(stringToByte(entityToJson(newEntity)))
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

    private byte[] stringToByte(String str) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(str);
            oos.close();
            return baos.toByteArray();
        } catch (IOException e) {
//            e.printStackTrace();
            return null;
        }
    }

    private String byteToString(byte byteValue) {
        return String.valueOf((char) byteValue);
    }

    private String byteToHexadecimal(byte[] bytes) {
        StringBuilder hexadecimalString = new StringBuilder();
        for (byte b : bytes) {
            int maskedByte = b & 0xFF;
            String hex = Integer.toHexString(maskedByte);
            if (hex.length() == 1) {
                hexadecimalString.append('0');
            }
            hexadecimalString.append(hex);
        }
        return hexadecimalString.toString();
    }

    private byte hexadecimalToByte(String hexadecimal) {
        if (hexadecimal.length() != 2) {
            throw new IllegalArgumentException("A string hexadecimal deve ter exatamente 2 caracteres.");
        }
        return (byte) Integer.parseInt(hexadecimal, 16);
    }
}

