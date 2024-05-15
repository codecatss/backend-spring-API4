package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    boolean existsByUsername(String username);

    Partner findByUsername(String username);
}
