package Oracle.Partner.Tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Oracle.Partner.Tracker.entities.Certification;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    //
}
