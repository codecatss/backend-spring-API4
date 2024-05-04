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

public class CsvService {
	public List<String[]> processCsv(MultipartFile file) {
        try {
             return readCsvData(file);
        } catch (Exception e) {
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
