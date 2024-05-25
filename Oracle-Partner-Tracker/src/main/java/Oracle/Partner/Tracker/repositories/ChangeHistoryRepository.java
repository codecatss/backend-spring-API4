package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.ChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChangeHistoryRepository extends JpaRepository<ChangeHistory, Long> {

    @Query(value = "select username, change_type, table_name, name, old_value, new_value from view_change_history", nativeQuery = true)
    List<Object[]> getAllFromChangeHistoryView();

    @Query(value = "select username, change_type, table_name, name, old_value, new_value, changed_at from view_change_history where table_name = ?1 and record_id = ?2", nativeQuery = true)
    List<Object[]> getByIdFromChangeHistoryView(String tableName, String recordId);

    @Query(value = "select record_id, name, table_name, count(*), max(changed_at) from view_change_history group by record_id, name, table_name", nativeQuery = true)
    List<Object[]> getAllGroupByFromChangeHistoryView();

}
