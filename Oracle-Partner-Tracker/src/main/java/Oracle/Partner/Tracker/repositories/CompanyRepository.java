package Oracle.Partner.Tracker.repositories;

import java.util.Optional;
import Oracle.Partner.Tracker.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository <Company,Long>{
    boolean existsByCnpj(String cnpj);
    Optional<Company> findById(Long id);
    Company findByName(String name);
}
