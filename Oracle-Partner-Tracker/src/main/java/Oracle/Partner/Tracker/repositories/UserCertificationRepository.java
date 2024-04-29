package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.relations.UserCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;


import org.springframework.data.repository.query.Param;

public interface UserCertificationRepository extends JpaRepository <UserCertification,String>{

@Query("SELECT u.name AS userName, c.name AS companyName, ce.name AS certificationName, uc.expiresAt AS expirationDate, uc.status AS certificationStatus " +
       "FROM UserCertification uc " +
       "INNER JOIN uc.user u " +
       "INNER JOIN u.company c " +
       "INNER JOIN uc.certification ce " +
       "WHERE uc.expiresAt BETWEEN :currentDate AND :expiresAt")
List<Object[]> getUserCertifications(@Param("currentDate") LocalDateTime currentDate, @Param("expiresAt") LocalDateTime expiresAt);

    
}