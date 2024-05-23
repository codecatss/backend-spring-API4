package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.CompanyDTO;
import Oracle.Partner.Tracker.dto.GenericDTO;
import Oracle.Partner.Tracker.dto.EmployeeDTO;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.entities.Employee;
import Oracle.Partner.Tracker.repositories.EmployeeRepository;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.MembershipEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements GenericService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyService companyService;

    public List<Employee> findAllUsers() {
        List<Employee> allEmployees = employeeRepository.findAll();
        if (allEmployees.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return allEmployees;
    }

    public Employee findUserById(Long id) {
        Optional<Employee> user = employeeRepository.findById(id);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    public Employee registerNewUser(EmployeeDTO user) {
        if (employeeRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        CompanyDTO companyDto = companyService.findCompanyByCnpj(user.getCnpjCompanyString());
        if (companyDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        Company company = new Company(companyDto);
        company.setId(companyDto.getId());
        user.setCompany(company);
        user.setStatusString("ACTIVE");
        user.setIngestionOperation(IngestionOperation.toIngestionOperation("MANUAL"));
        user.setMemberShipType(MembershipEnum.toMembership("PRINCIPAL"));

        return employeeRepository.save(new Employee(user));
    }

        public Employee updateUser(Long id, EmployeeDTO employeeDTO){
        Optional<Employee> user = employeeRepository.findById(id);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Employee employeeUpdate = user.get();
        employeeUpdate.setUpdateAt(LocalDateTime.now());

        BeanUtils.copyProperties(employeeDTO, employeeUpdate);
        return employeeRepository.save(employeeUpdate);
    }

    public void deleteUser(Long id) {
        Optional<Employee> user = employeeRepository.findById(id);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Class<?> getDtoClass() {
        return EmployeeDTO.class;
    }

    @Override
    public void saveAllGenericDTO(List<GenericDTO> genericDTOList) {
        for(GenericDTO genericDTO : genericDTOList){
            EmployeeDTO employeeDTO = (EmployeeDTO) genericDTO;
            if(!employeeRepository.existsByEmail(employeeDTO.getEmail())){
                CompanyDTO companyDTO = companyService.findCompanyByCnpj(employeeDTO.getCnpjCompanyString());
                Company company = new Company(companyDTO);
                company.setId(companyDTO.getId());
                employeeDTO.setCompany(company);
                Employee employee = new Employee(employeeDTO);
                employeeRepository.save(employee);
            }
        }
    }
}
