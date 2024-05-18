package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.ChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeHistoryRepository extends JpaRepository<ChangeHistory, Long> {

}
