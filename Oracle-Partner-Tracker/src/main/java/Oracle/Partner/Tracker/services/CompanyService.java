package Oracle.Partner.Tracker.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Oracle.Partner.Tracker.utils.OPNStatus;
import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.repositories.CompanyRepository;

@Service
public class CompanyService extends CsvService<CompanyDTO>{

    @Autowired
    private CompanyRepository companyRepository;

    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyDTO getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.map(CompanyDTO::new).orElse(null);
    }

    public CompanyDTO findCompanyByCnpj(String cnpj){
        Optional<Company> company = Optional.ofNullable(companyRepository.findByCnpj(cnpj));
        return company.map(CompanyDTO::new).orElse(null);
    }

    public Optional<CompanyDTO> findCompanyByName(String name){
        Optional<Company> company = Optional.ofNullable(companyRepository.findByName(name));
        return Optional.ofNullable(company).orElse(null).map(CompanyDTO::new);
    }

    public List<Company> findAllCompanies(){
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    public Optional<CompanyDTO> insertCompany(CompanyDTO companyDTO) {   

        // Verificando se o CNPJ da empresa é válido
        if (companyDTO.getCnpj() == null || companyDTO.getCnpj().isBlank()) {
            throw new IllegalArgumentException("O CNPJ da empresa é obrigatório.");
        }

        if (companyDTO.getName() == null || companyDTO.getName().isBlank()){
            throw new IllegalArgumentException("O nome da empresa é obrigatório.");
        }

        // Verificando se o país da empresa é válido
        if (companyDTO.getCountry() == null || companyDTO.getCountry().isBlank()) {
            throw new IllegalArgumentException("O país da empresa é obrigatório.");
        }

        // Verificando se o estado da empresa é válido
        if (companyDTO.getState() == null || companyDTO.getState().isBlank()) {
            throw new IllegalArgumentException("O estado da empresa é obrigatório.");
        }

        // Verificando se a cidade da empresa é válida
        if (companyDTO.getCity() == null || companyDTO.getCity().isBlank()) {
            throw new IllegalArgumentException("A cidade da empresa é obrigatória.");
        }

        // Verificando se o endereço da empresa é válido
        if (companyDTO.getAddress() == null || companyDTO.getAddress().isBlank()) {
            throw new IllegalArgumentException("O endereço da empresa é obrigatório.");
        }

        Company company = new Company();

        copyDTOtoEntity(companyDTO,company);

        company = companyRepository.save(company);

        return Optional.of(new CompanyDTO(company));
    }


    public CompanyDTO updateCompany(Long id,CompanyDTO companyDTO) {

        Company company = companyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Company not found with id: " + id)
        );
        copyDTOtoEntity(companyDTO, company);
        company = companyRepository.save(company);
        return new CompanyDTO(company);
    }

    public void disableCompany(Long id){
        Company company = companyRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Company não encontrada com o id: " + id)
            );
        company.setStatus(Status.INACTIVE);
        companyRepository.save(company);
    }

    public void enableCompany(Long id){
        Company company = companyRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Workload não encontrada com o id: " + id)
            );
            company.setStatus(Status.ACTIVE);
            companyRepository.save(company);

        }


    private void copyDTOtoEntity(CompanyDTO companyDTO, Company company) {
        company.setName(companyDTO.getName());
        company.setAddress(companyDTO.getAddress());
        company.setCity(companyDTO.getCity());
        company.setState(companyDTO.getState());
        company.setCountry(companyDTO.getCountry());
        company.setCnpj(companyDTO.getCnpj());
        company.setCreditHold(companyDTO.getCreditHold());
        company.setSlogan(companyDTO.getSlogan());
        company.setIngestionOperation(companyDTO.getIngestionOperation());
        company.setOpnStatus(companyDTO.getOpnStatus());

        if (companyDTO.getStatus() == null || companyDTO.getStatus().name().isBlank()){
            companyDTO.setStatus(Status.ACTIVE);
        }
        company.setStatus(companyDTO.getStatus());
        if(companyDTO.getCreateAt() == null || companyDTO.getCreateAt().toString().isBlank()){
            company.setCreateAt(LocalDateTime.now());
        }else{
            company.setCreateAt(companyDTO.getCreateAt());
        }
        company.setUpdateAt(LocalDateTime.now());
    }


    @Override
    public List<CompanyDTO> mapCsvToEntities(List<String[]> csvData) {

        String[] header = csvData.get(0);
        List<CompanyDTO> companies = new ArrayList<>();


        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);

            Optional<CompanyDTO> companyDTO =  mapRowToCompany(row, header);
            if (companyDTO != null){
                companies.add(companyDTO.get());
            }
        }

        return companies;
    }
    public Optional<CompanyDTO> mapRowToCompany(String[] row, String[] header) {
        CompanyDTO companyDTO = new CompanyDTO();
    
        for (int i = 0; i < header.length; i++) {
            companyDTO.setIngestionOperation(IngestionOperation.CSV);
            switch (header[i]) {
                case "Company Name":
                    String companyName = row[i];
                    if (companyName != null && !companyName.isBlank()) {
                        companyDTO.setName(companyName);
                    } else {
                        // Se o nome da empresa for nulo ou vazio, pule esta linha
                        return Optional.empty();
                    }
                    break;
                case "Status":
                    if(row[i].equals("Active")){
                        companyDTO.setStatus(Status.ACTIVE);
                    }else{
                        companyDTO.setStatus(Status.INACTIVE);
                    }
                    break;
                case "OPN Status":
                    if(row[i].equals("Active")){
                        companyDTO.setOpnStatus(OPNStatus.MEMBER);
                    }else{
                        companyDTO.setOpnStatus(OPNStatus.EXPIRED);
                    };
                    break;
                case "Company ID":
                    companyDTO.setCnpj(row[i]);
                    break;
                case "State":
                    companyDTO.setState(row[i]);
                    break;
                case "Country":
                    companyDTO.setCountry(row[i]);
                    break;
                case "City":
                    companyDTO.setCity(row[i]);
                    break;
                case "Address":
                    companyDTO.setAddress(row[i]);
                    break;
                case "Credit Hold":
                    companyDTO.setCreditHold(row[i]);
                    break;
                case "Slogan":
                    companyDTO.setSlogan(row[i]);
                    break;
                default:
                    break;
            }
        }
    
        // Verifique se o nome da empresa foi definido
        if (companyDTO.getName() == null || companyDTO.getName().isBlank()) {
            // Se o nome da empresa ainda for nulo ou vazio, retorne Optional.empty()
            return Optional.empty();
        }
    
        // Verifique se a empresa já existe no banco de dados antes de inserir
        CompanyDTO optionalCompany = this.findCompanyByCnpj(companyDTO.getCnpj());
        if (optionalCompany != null) {

            // Se a empresa já existir, retorne Optional.empty()
            return null;
        }
    
        Company company = new Company();
        copyDTOtoEntity(companyDTO, company);
    
        // Insira a empresa no banco de dados
        company = companyRepository.save(company);
    
        return Optional.of(new CompanyDTO(company));
    }
    

}    