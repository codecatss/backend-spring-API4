package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.repositories.CompanyRepository;
import Oracle.Partner.Tracker.utils.companyEnum.CompanyStatus;
import Oracle.Partner.Tracker.utils.companyEnum.OpnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyService implements GenericService<CompanyDTO> {

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public CompanyDTO findCompanyById(String id){
        Company company = companyRepository.findById(id).get();
        return new CompanyDTO(company);
    }


    @Transactional(readOnly = true)
    public Page<CompanyDTO> findAllCompanies(Pageable pageable){
        Page<Company> companies = companyRepository.findAll(pageable);
        return companies.map(CompanyDTO::new);
    }



    public CompanyDTO insertCompany(CompanyDTO companyDTO) {
        // Verificando se o nome da empresa é válido
        if (companyDTO.getName() == null || companyDTO.getName().isBlank()) {
            throw new IllegalArgumentException("O nome da empresa é obrigatório.");
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

        // Verificando se o CEP da empresa é válido
        if (companyDTO.getCep() == null || companyDTO.getCep().isBlank()) {
            throw new IllegalArgumentException("O CEP da empresa é obrigatório.");
        }

        Company company = new Company();

        copyDTOtoEntity(companyDTO,company);

        company = companyRepository.save(company);

        return new CompanyDTO(company);
    }


    public CompanyDTO updateCompany(String id,CompanyDTO companyDTO) {

        Company company = companyRepository.getReferenceById(id);


        copyDTOtoEntity(companyDTO,company);



        company = companyRepository.save(company);

        return new CompanyDTO(company);
    }


    public void disableCompany(UUID id){
        Company company = companyRepository.findById(String.valueOf(id)).orElse(null);
        if(company != null){
            company.setCompanyStatus(CompanyStatus.ACTIVE);
            companyRepository.save(company);
        }else{
            throw new RuntimeException("Company not found with id: " + id);
        }
    }

    public void enableCompany(UUID id){
        Company company = companyRepository.findById(String.valueOf(id)).orElse(null);
        if(company != null){
            company.setCompanyStatus(CompanyStatus.ACTIVE);
            companyRepository.save(company);

        }else{
            throw new RuntimeException("Company not found with id: " + id);
        }
    }


    private void copyDTOtoEntity(CompanyDTO companyDTO, Company company) {
        company.setName(companyDTO.getName());
        company.setOpnStatus(companyDTO.getOpnStatus());
        company.setCnpj(companyDTO.getCnpj());
        company.setCountry(companyDTO.getCountry());
        company.setState(companyDTO.getState());
        company.setCity(companyDTO.getCity());
        company.setAddress(companyDTO.getAddress());
        company.setCep(companyDTO.getCep());
        company.setCreateOn(LocalDateTime.now());
        company.setCreditHold(companyDTO.getCreditHold());
        company.setSlogan(companyDTO.getSlogan());
        company.setCompanyStatus(companyDTO.getCompanyStatus());

    }


    @Override
    public List<CompanyDTO> mapCsvToEntities(List<String[]> csvData) {

        String[] header = csvData.get(0);
        List<CompanyDTO> companies = new ArrayList<>();


        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);

            CompanyDTO company =  mapRowToCompany(row, header);
            companies.add(company);
        }

        return companies;
    }
    public CompanyDTO mapRowToCompany(String[] row, String[] header) {
        CompanyDTO companyDTO = new CompanyDTO();


        for (int i = 0; i < header.length; i++) {

            switch (header[i]) {
                case "Company Name":
                    companyDTO.setName(row[i]);
                    break;
                case "OPN Status":
                    if(row[i].equals("Active")){
                        companyDTO.setOpnStatus(OpnStatus.ACTIVE);

                    } else {
                        companyDTO.setOpnStatus(OpnStatus.EXPIRED);
                    }
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





        Company company = new Company();

        copyDTOtoEntity(companyDTO, company);
        company.setCompanyStatus(CompanyStatus.ACTIVE);


        companyRepository.save(company);

        return companyDTO;
    }






}
