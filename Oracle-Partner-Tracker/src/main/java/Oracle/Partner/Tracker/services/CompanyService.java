package Oracle.Partner.Tracker.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import Oracle.Partner.Tracker.dto.CompanyRecord;
import Oracle.Partner.Tracker.dto.GenericDTO;
import Oracle.Partner.Tracker.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.repositories.CompanyRepository;

@Service
public class CompanyService implements GenericService{
    @Autowired
    private CompanyRepository companyRepository;

    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void saveCompany(List<Company> companies){
        companyRepository.saveAll(companies);
    }

    public void saveCompany(CompanyDTO companyDTO){
        Company company = new Company(companyDTO);
        companyRepository.save(company);
    }

    public void saveCompany(CompanyRecord companyRecord){
        Company company = new Company(companyRecord);
        companyRepository.save(company);
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

    public List<Company> findAllCompiniesActive(){
        List<Company> companies = companyRepository.findAllByStatus(Status.ACTIVE);
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

    @Override
    public Class<?> getDtoClass() {
        return CompanyDTO.class;
    }

    @Override
    public void saveAllGenericDTO(List<GenericDTO> genericDTOList) {
        for(GenericDTO genericDTO : genericDTOList){
            CompanyDTO companyDTO = (CompanyDTO) genericDTO;
            if(companyRepository.findByCnpj(companyDTO.getCnpj()) == null){
                companyRepository.save(new Company(companyDTO));
            }
        }
    }
}    