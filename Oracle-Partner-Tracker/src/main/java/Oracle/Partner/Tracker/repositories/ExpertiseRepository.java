package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.Expertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ExpertiseRepository extends JpaRepository <Expertise,String>{
    @Query(value =
            "SELECT " +
            "(SELECT COUNT(c) FROM Company c WHERE c.opnStatus = 'MEMBER') AS qtyPartners, " +
            "(SELECT COUNT(c) FROM Company c WHERE c.opnStatus = 'MEMBER' AND c.status = 'ACTIVE') AS qtyPartnersActive, " +
            "(SELECT COUNT(c) FROM Company c WHERE c.opnStatus = 'MEMBER' AND c.status = 'INACTIVE') AS qtyPartnersInactive, " +
            "(SELECT AVG(sub.repeatId) FROM (SELECT COUNT(op.id) AS repeatId FROM CompanyOpnTrack co LEFT JOIN co.opnTrack op GROUP BY op.id) AS sub) AS averageTracksPerPartners, " +
            "(SELECT COUNT(u) FROM User u WHERE u.status = 'ACTIVE') AS qtyUsers, " +
            "(SELECT COUNT(o) FROM OpnTrack o WHERE o.status = 'ACTIVE') AS qtyTracks, " +
            "(SELECT COUNT(e) FROM Expertise e WHERE e.status = 'ACTIVE') AS DashboardDTO " +
            "FROM Company c")
    List<Object[]> getDashboardDTO();

    @Query("select e.name, count(*) from OpnTrackExpertise opne join opne.expertise e group by e.name")
    List<Object[]> getExpertiseUsageCount();

}
