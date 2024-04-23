package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.dto.StatePerCompany;
import Oracle.Partner.Tracker.entities.OpnTrack;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OpnTrackRepository extends JpaRepository <OpnTrack,Long>{
    Optional<OpnTrack> findById(Long id);

    OpnTrack findByName(String name);

    @Query(value = "select new Oracle.Partner.Tracker.dto.StatePerCompany(opn.name, COUNT(*) as companyCount) from OpnTrack opn join Company c")
    List<StatePerCompany> getOpnTrackContByCompany();
} 
