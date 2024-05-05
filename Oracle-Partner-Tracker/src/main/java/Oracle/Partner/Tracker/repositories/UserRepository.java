package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
