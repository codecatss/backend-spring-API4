package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.dto.UserDTO;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public abstract class CsvService<T> implements GenericService<T>{
	public List<T> processCsv(MultipartFile file) {
        try {
            List<String[]> csvData = readCsvData(file);

            return this.mapCsvToEntities(csvData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ExpertiseDTO> processCsvExpertise(MultipartFile file){
//        return expertiseService.mapCsvToExpertise(file);
        return null;
    }

    private List<String[]> readCsvData(MultipartFile file) throws Exception {

        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        CSVReader csvReader = new CSVReader(reader);

        List<String[]> csvData = csvReader.readAll();

        csvReader.close();
        reader.close();
        return csvData;
    }

    public List<UserDTO> processCsvUser(MultipartFile file, UserService userService) {
        try {
            List<String[]> csvData = readCsvData(file);

            // Chame o método mapCsvToEntities do UserService
            return userService.mapCsvToEntities(csvData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
