package Oracle.Partner.Tracker.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import Oracle.Partner.Tracker.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository <Company,Long>{
    boolean existsByCnpj(String cnpj);
    Optional<Company> findById(Long id);
    Company findByName(String name);
    Company findByCnpj(String cnpj);

    @Query(value = "SELECT * FROM company;", nativeQuery = true)
    List<Object[]> getCompaniesByState();

}
