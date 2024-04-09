package Oracle.Partner.Tracker.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Oracle.Partner.Tracker.dto.OpnTrackDTO;
import Oracle.Partner.Tracker.entities.OpnTrack;
import Oracle.Partner.Tracker.repositories.OpnTrackRepository;
import Oracle.Partner.Tracker.util.Status;
import Oracle.Partner.Tracker.utils.companyEnum.IngestionOperation;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.repositories.CompanyRepository;

import java.util.UUID;

@Service
public class CompanyService extends CsvService<CompanyDTO>{

    private CompanyRepository companyRepository;

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyDTO getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.map(CompanyDTO::new).orElse(null);
    }
    

    public Optional<CompanyDTO> findCompanyByName(String name){
        Optional<Company> company = Optional.ofNullable(companyRepository.findByName(name));
        return Optional.ofNullable(company).orElse(null).map(CompanyDTO::new);
    }

    public Page<CompanyDTO> findAllCompanies(Pageable pageable){
        Page<Company> companies = companyRepository.findAll(pageable);
        return companies.map(CompanyDTO::new);
    }

    public Optional<CompanyDTO> findWorkloadByName(String name){
        Optional<Company> workload = Optional.ofNullable(companyRepository.findByName(name));
        return Optional.ofNullable(workload).orElse(null).map(CompanyDTO::new);
    }

    public Optional<CompanyDTO> insertCompany(CompanyDTO companyDTO) {
        // Verificando se o nome da empresa é válido
        Optional<CompanyDTO> optionalWorkload= this.findWorkloadByName(companyDTO.getName());
        if (optionalWorkload.isPresent()){
            return Optional.empty();
        }
        if (companyDTO.getName() == null || companyDTO.getName().isBlank()){
            throw new RuntimeException("O nome da Workload é obrigatório");
        }        

        // Verificando se o CNPJ da empresa é válido
        if (companyDTO.getCnpj() == null || companyDTO.getCnpj().isBlank()) {
            throw new IllegalArgumentException("O CNPJ da empresa é obrigatório.");
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
        company.setName(company.getName());
        company.setIngestionOperation(company.getIngestionOperation());
        if (companyDTO.getStatus() == null || companyDTO.getStatus().name().isBlank()){
            companyDTO.setStatus(Status.ACTIVE);
        }
        company.setStatus(companyDTO.getStatus());
        if(companyDTO.getCreated_at() == null || companyDTO.getCreated_at().toString().isBlank()){
            company.setCreatedAt(LocalDateTime.now());
        }else{
            company.setCreatedAt(companyDTO.getCreated_at());
        }
        company.setUpdatedAt(LocalDateTime.now());
    }


    @Override
    public List<CompanyDTO> mapCsvToEntities(List<String[]> csvData) {

        String[] header = csvData.get(0);
        List<CompanyDTO> companies = new ArrayList<>();


        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);

            Optional<CompanyDTO> companyDTO =  mapRowToCompany(row, header);
            if (companyDTO.isPresent()){
                companies.add(companyDTO.get());
            }
        }

        return companies;
    }
    public Optional<CompanyDTO> mapRowToCompany(String[] row, String[] header) {
        CompanyDTO companyDTO = new CompanyDTO();
    
        for (int i = 0; i < header.length; i++) {
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
                case "Company Status":
                    companyDTO.setStatus(Status.toStatus(row[i]));
                    break;
                case "Company ID":
                    companyDTO.setCnpj(row[i]);
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
        Optional<CompanyDTO> optionalCompany = this.findCompanyByName(companyDTO.getName());
        if (optionalCompany.isPresent()) {
            // Se a empresa já existir, retorne Optional.empty()
            return Optional.empty();
        }
    
        Company company = new Company();
        copyDTOtoEntity(companyDTO, company);
    
        // Insira a empresa no banco de dados
        company = companyRepository.save(company);
    
        return Optional.of(new CompanyDTO(company));
    }
    

}    