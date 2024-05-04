package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.dto.UserDTO;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class CsvService {

    @Autowired
    private List<GenericService> genericServices;

    public void mapCsvToEntities(MultipartFile file){
        List<String[]> csvData = processCsv(file);
        assert csvData != null;
        String[] header = csvData.get(0);

        for(CompanyDTO dto : teste2teste(file)){
            System.out.println(dto);
        }

//        for(GenericService genericService : this.genericServices){
//            System.out.println(genericService.getClass());
//        }

    }

    public List<CompanyDTO> teste2teste(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<CompanyDTO> csvToBean = new CsvToBeanBuilder<CompanyDTO>(reader)
                    .withType(CompanyDTO.class)
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
