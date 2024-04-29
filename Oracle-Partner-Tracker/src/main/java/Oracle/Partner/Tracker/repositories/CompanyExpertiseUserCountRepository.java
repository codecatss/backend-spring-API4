package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.CompanyExpertiseUserCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyExpertiseUserCountRepository extends JpaRepository<CompanyExpertiseUserCount, String> {


}