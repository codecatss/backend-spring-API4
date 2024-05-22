package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.ChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChangeHistoryRepository extends JpaRepository<ChangeHistory, Long> {
    @Query(value = "select p.username, c.recordId, c.tableName, c.changeType, c.oldValueHexadecimal, c.newValueHexadecimal, c.changedAt from ChangeHistory c left join c.changedByPartnerId p order by c.changedAt desc")
    List<Object[]> getAllOrderByChangeDate();
}
