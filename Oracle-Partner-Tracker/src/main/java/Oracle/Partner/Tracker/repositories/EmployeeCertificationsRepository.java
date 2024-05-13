package Oracle.Partner.Tracker.repositories;

import Oracle.Partner.Tracker.entities.relations.EmployeeCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.time.LocalDateTime;


import org.springframework.data.repository.query.Param;

public interface EmployeeCertificationsRepository extends JpaRepository <EmployeeCertification,String>{

    @Query("SELECT u.name AS userName, c.name AS companyName, ce.name AS certificationName, " + 
    "uc.expiresAt AS expirationDate, uc.status AS certificationStatus, " + 
    "DATEDIFF(uc.expiresAt, :currentDate) AS daysUntilExpiration " +
    "FROM EmployeeCertification uc " +
    "INNER JOIN uc.employee u " +
    "INNER JOIN u.company c " +
    "INNER JOIN uc.certification ce " +
    "WHERE uc.expiresAt BETWEEN :currentDate AND :expiresAt")
List<Object[]> getEmployeeCertifications(@Param("currentDate") LocalDateTime currentDate, @Param("expiresAt") LocalDateTime expiresAt);

}