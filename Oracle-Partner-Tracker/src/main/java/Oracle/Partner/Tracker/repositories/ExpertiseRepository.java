package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.entities.Expertise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertiseRepository extends JpaRepository <Expertise,Long>{
}
