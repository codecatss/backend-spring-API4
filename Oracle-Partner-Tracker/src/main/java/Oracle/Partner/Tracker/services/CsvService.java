package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ExpertiseService expertiseService;

    public List<CompanyDTO> processCsv(MultipartFile file) {
        try {
            List<String[]> csvData = readCsvData(file);
            return companyService.mapCsvToCompanies(csvData);
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
