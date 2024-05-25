package Oracle.Partner.Tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import Oracle.Partner.Tracker.dto.GenericDTO;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public class CsvService {

    @Autowired
    private List<GenericService> genericServices;

    public void mapCsvToEntities(MultipartFile file){
        try{
            for(GenericService genericService : genericServices){
                genericService.saveAllGenericDTO(mapCsvEntitiesToList(file, genericService));
            }
        }
        catch (Exception error){
            error.printStackTrace();
        }
    }

    public List<GenericDTO> mapCsvEntitiesToList(MultipartFile file, GenericService genericService) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<GenericDTO> csvToBean = new CsvToBeanBuilder<GenericDTO>(reader)
                    .withType((Class<? extends GenericDTO>) genericService.getDtoClass())
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
