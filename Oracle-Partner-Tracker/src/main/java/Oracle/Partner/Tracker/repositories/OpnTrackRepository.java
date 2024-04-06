package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.OpnTrack;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OpnTrackRepository extends JpaRepository <OpnTrack,String>{
    OpnTrack findByName(String name);   
} 
