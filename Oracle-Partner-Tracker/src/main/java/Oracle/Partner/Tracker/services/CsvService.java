package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.dto.GenericDTO;
import Oracle.Partner.Tracker.entities.Company;
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
    private ExpertiseService expertiseService;
    @Autowired
    private CompanyService companyService;

    public void mapCsvToEntities(MultipartFile file){
        System.out.println("Iniciando para salvar o csv no banco de dados...");
        try{
            for(GenericService genericService : genericServices){
                System.out.println("Salvando: "+genericService.getClass());
                genericService.saveAllGenericDTO(mapCsvEntitiesToList(file, genericService));
                System.out.println("Salvo!\n");
            }
            System.out.println("Finalizou todo o processo sem erros");
            System.out.println("\nDone!");
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
