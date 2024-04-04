package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository <Company,String>{
    boolean existsByCnpj(String cnpj);
}
