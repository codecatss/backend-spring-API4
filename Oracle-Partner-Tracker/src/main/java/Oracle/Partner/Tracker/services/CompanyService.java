package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

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
        company = companyRepository.save(company);

        return new CompanyDTO(company);
    }


}
