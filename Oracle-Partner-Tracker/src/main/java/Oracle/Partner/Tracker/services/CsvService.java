package Oracle.Partner.Tracker.services;

import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public abstract class CsvService<T> {

    protected final GenericService<T> service;

    public CsvService(GenericService<T> service) {
        this.service = service;
    }

    public List<T> processCsv(MultipartFile file) {
        try {

            List<String[]> csvData = readCsvData(file);

            return service.mapCsvToEntities(csvData);
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
