package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.OpnTrack;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OpnTrackRepository extends JpaRepository <OpnTrack,Long>{
    Optional<OpnTrack> findById(Long id);

    OpnTrack findByName(String name);   
} 
