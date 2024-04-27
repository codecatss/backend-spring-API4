package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.dto.TrackPerCompany;
import Oracle.Partner.Tracker.entities.OpnTrack;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OpnTrackRepository extends JpaRepository <OpnTrack,Long>{
    Optional<OpnTrack> findById(Long id);

    OpnTrack findByName(String name);

    @Query("select new Oracle.Partner.Tracker.dto.TrackPerCompany(opt.name, COUNT(*)) FROM CompanyOpnTrack cot JOIN cot.opnTrack opt GROUP BY opt.name")
    List<TrackPerCompany> getOpnTrackUsageCount();

} 
