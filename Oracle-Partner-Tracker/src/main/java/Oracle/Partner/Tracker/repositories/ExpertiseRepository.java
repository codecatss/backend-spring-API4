package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.Expertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ExpertiseRepository extends JpaRepository <Expertise,String>{
    @Query(value = "select * from DashboardDTO;", nativeQuery = true)
    public List<Object[]> getDashboardDTO();

    @Query(value = "select * from TrackPerCompany;", nativeQuery = true)
    public List<Object[]> getTrackPerCompany();
}
