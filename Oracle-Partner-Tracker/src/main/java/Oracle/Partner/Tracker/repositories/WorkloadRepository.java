package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.Workload;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkloadRepository extends JpaRepository <Workload,Long>{
    Workload findByName(String name);
}
