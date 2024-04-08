package Oracle.Partner.Tracker.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import Oracle.Partner.Tracker.dto.ExpertiseDTO;

public abstract class CsvService<T> implements GenericService<T>{
    private ExpertiseService expertiseService;

    @Autowired
    public void setExpertiseService(ExpertiseService expertiseService) {
        this.expertiseService = expertiseService;
    }

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
        return expertiseService.mapCsvToExpertise(file);
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
