package Oracle.Partner.Tracker.services;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    private static CompanyRepository companyRepository;

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
        return csvData.stream().skip(1)
                .map(row -> mapRowToCompany(row, header))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public Company createCompany(CompanyDTO companyDTO) {
        Company company = new Company();
        copyDTOtoEntity(companyDTO, company);
        return companyRepository.save(company);
    }

    public static String getCompanyId(String name){
        Company company = companyRepository.findByName(name);
        return company.getId();
    }

    public Optional<CompanyDTO> mapRowToCompany(String[] row, String[] header) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setIngestionOperation(IngestionOperation.CSV);

        Map<String, BiConsumer<String, CompanyDTO>> fieldSetterMap = new HashMap<>();
        fieldSetterMap.put("Company Name", (value, dto) -> dto.setName(value));
        fieldSetterMap.put("Status", (value, dto) -> dto.setStatus("Active".equals(value) ? Status.ACTIVE : Status.INACTIVE));
        fieldSetterMap.put("OPN Status", (value, dto) -> dto.setOpnStatus("Active".equals(value) ? OPNStatus.MEMBER : OPNStatus.EXPIRED));
        fieldSetterMap.put("Company ID", (value, dto) -> dto.setCnpj(value));
        fieldSetterMap.put("State", (value, dto) -> dto.setState(value));
        fieldSetterMap.put("Country", (value, dto) -> dto.setCountry(value));
        fieldSetterMap.put("City", (value, dto) -> dto.setCity(value));
        fieldSetterMap.put("Address", (value, dto) -> dto.setAddress(value));
        fieldSetterMap.put("Credit Hold", (value, dto) -> dto.setCreditHold(value));
        fieldSetterMap.put("Slogan", (value, dto) -> dto.setSlogan(value));

        IntStream.range(0, header.length).forEach(i -> {
            BiConsumer<String, CompanyDTO> fieldSetter = fieldSetterMap.get(header[i]);
            if (fieldSetter != null) {
                fieldSetter.accept(row[i], companyDTO);
            }
        });

        if (companyDTO.getName() == null || companyDTO.getName().isBlank()) {
            return Optional.empty();
        }

        if (this.findCompanyByCnpj(companyDTO.getCnpj()) != null) {
            return Optional.empty();
        }


        Company company = new Company();
        copyDTOtoEntity(companyDTO, company);
        company = companyRepository.save(company);

        getCompanyId(companyDTO.getName());

        return Optional.of(new CompanyDTO(company));
    }
    

}    