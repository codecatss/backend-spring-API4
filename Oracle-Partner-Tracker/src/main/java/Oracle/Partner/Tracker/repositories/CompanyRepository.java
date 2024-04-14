package Oracle.Partner.Tracker.repositories;

import java.util.Optional;

import Oracle.Partner.Tracker.dto.StatePerCompany;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import Oracle.Partner.Tracker.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository <Company,Long>{
    boolean existsByCnpj(String cnpj);
    Optional<Company> findById(Long id);
    Company findByName(String name);
    Company findByCnpj(String cnpj);

    @Query(value = "select * from StatePerCompany;", nativeQuery = true)
    List<Object[]> getCompaniesByState();

}
