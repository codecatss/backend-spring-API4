package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.dto.GenericDTO;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class CsvService {

    @Autowired
    private List<GenericService> genericServices;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;


    public void mapCsvToEntities(MultipartFile file){
        List<String[]> csvData = processCsv(file);
        assert csvData != null;
        String[] header = csvData.get(0);

        for(GenericDTO companyDTO : mapCsvEntitiesToList(file, companyService)){
            System.out.println(companyDTO);
        }


//        for(GenericService genericService : genericServices){
//            System.out.println(genericService.getClass());
//            for(GenericDTO companyDTO : mapCsvEntitiesToList(file, genericService)){
//                System.out.println(companyDTO);
//            }
//        }

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

    private void test(String[] header, String[] row){
        for (int i = 0; i < header.length; i++) {

        }

    }

    private List<String[]> processCsv(MultipartFile file) {
        try {
            return readCsvData(file);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String[]> readCsvData(MultipartFile file) throws Exception {
        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvData = csvReader.readAll();
        csvReader.close();
        reader.close();
        return csvData;
    }
}
