package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
}
