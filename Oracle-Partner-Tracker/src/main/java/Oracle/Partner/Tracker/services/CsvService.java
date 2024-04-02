package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    // Injeção de dependência do repositório CompanyRepository
    @Autowired
    private CompanyRepository companyRepository;

    // Método para processar um arquivo CSV e retornar uma lista de objetos CompanyDTO
    public List<CompanyDTO> processCsv(MultipartFile file) {
        try {
            // Lê os dados do arquivo CSV
            List<String[]> csvData = readCsvData(file);
            // Mapeia os dados do CSV para objetos CompanyDTO
            return mapCsvToCompanies(csvData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para ler os dados de um arquivo CSV e retornar uma lista de arrays de strings
    private List<String[]> readCsvData(MultipartFile file) throws Exception {
        // Cria um leitor para o arquivo CSV
        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        CSVReader csvReader = new CSVReader(reader);
        // Lê todos os dados do arquivo CSV
        List<String[]> csvData = csvReader.readAll();
        // Fecha os leitores
        csvReader.close();
        reader.close();
        return csvData;
    }

    // Método para mapear os dados do CSV para objetos CompanyDTO
    private List<CompanyDTO> mapCsvToCompanies(List<String[]> csvData) {
        // Obtém o cabeçalho do CSV
        String[] header = csvData.get(0);
        List<CompanyDTO> companies = new ArrayList<>();

        // Itera sobre cada linha de dados no CSV
        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);
            // Mapeia uma linha de dados para um objeto CompanyDTO
            CompanyDTO company = mapRowToCompany(row, header);
            companies.add(company);
        }

        return companies;
    }

    // Método para mapear uma linha de dados para um objeto CompanyDTO
    // Método para mapear uma linha de dados para um objeto CompanyDTO
    private CompanyDTO mapRowToCompany(String[] row, String[] header) {
        CompanyDTO companyDTO = new CompanyDTO();

        // Itera sobre cada coluna no cabeçalho
        for (int i = 0; i < header.length; i++) {
            switch (header[i]) {
                case "Company Name":
                    // Define o nome da empresa no objeto CompanyDTO
                    if (row[i] != null) {
                        companyDTO.setName(row[i]);
                    } else {
                        companyDTO.setName("Company Name not found");
                    }
                    break;
                case "OPN Status":
                    // Define o status OPN da empresa no objeto CompanyDTO
                    if(row[i].equals("Active")){
                        companyDTO.setOpnStatus(true);
                        System.out.println(companyDTO.getOpnStatus());
                    } else {
                        companyDTO.setOpnStatus(false);
                    }
                    break;
                case "Company ID":
                    // Define o CNPJ da empresa no objeto CompanyDTO
                    companyDTO.setCnpj(row[i]);
                    break;
                case "Country":
                    // Define o país da empresa no objeto CompanyDTO
                    companyDTO.setCountry(row[i]);
                    break;
                case "City":
                    // Define a cidade da empresa no objeto CompanyDTO
                    companyDTO.setCity(row[i]);
                    break;
                case "Address":
                    // Define o endereço da empresa no objeto CompanyDTO
                    companyDTO.setAddress(row[i]);
                    break;
                case "Slogan":
                    // Define o slogan da empresa no objeto CompanyDTO
                    companyDTO.setSlogan(row[i]);
                    break;
                default:
                    break;
            }
        }



        // Cria um novo objeto Company e copia os dados do DTO para a entidade
        Company company = new Company();
        copyDTOtoEntity(companyDTO, company);

        // Salva a empresa no repositório
        companyRepository.save(company);

        return companyDTO;
    }


    // Método para copiar os dados de um DTO para uma entidade
    private void copyDTOtoEntity(CompanyDTO companyDTO, Company company) {
        company.setName(companyDTO.getName());
        company.setOpnStatus(companyDTO.getOpnStatus());
        company.setCnpj(companyDTO.getCnpj());
        company.setCountry(companyDTO.getCountry());
        company.setState(companyDTO.getState());
        company.setCity(companyDTO.getCity());
        company.setAddress(companyDTO.getAddress());
        company.setCep(companyDTO.getCep());
        company.setOpnStatus(companyDTO.getOpnStatus());
        company.setCreateOn(LocalDateTime.now());
        company.setCreditHold(companyDTO.getCreditHold());
        company.setSlogan(companyDTO.getSlogan());
        company.setCompanyStatus(companyDTO.getCompanyStatus());
    }
}
