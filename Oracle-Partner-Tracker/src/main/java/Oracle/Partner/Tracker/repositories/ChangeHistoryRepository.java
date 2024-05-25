package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.ChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChangeHistoryRepository extends JpaRepository<ChangeHistory, Long> {
    @Query(value = "select p.username, c.recordId, c.tableName, c.changeType, c.oldValue, c.newValue, c.changedAt from ChangeHistory c left join c.changedByPartnerId p order by c.changedAt desc")
    List<Object[]> getAllOrderByChangeDate();

    @Query(value = "select username,table_name,name,old_value,new_value from view_change_history", nativeQuery = true)
    List<Object[]> getAllFromChangeHistoryView();

    @Query(value = "select username, table_name, name, old_value, new_value from view_change_history where table_name = ?1 and record_id = ?2", nativeQuery = true)
    List<Object[]> getByIdFromChangeHistoryView(String tableName, String recordId);
}
