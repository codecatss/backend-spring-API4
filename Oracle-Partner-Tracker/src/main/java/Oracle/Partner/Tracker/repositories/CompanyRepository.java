package Oracle.Partner.Tracker.repositories;

import java.util.Objects;
import java.util.Optional;

import Oracle.Partner.Tracker.dto.StatePerCompany;
import Oracle.Partner.Tracker.dto.TrackPerCompany;
import Oracle.Partner.Tracker.utils.Status;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import Oracle.Partner.Tracker.entities.Company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository <Company,Long>{
    boolean existsByCnpj(String cnpj);
    Optional<Company> findById(Long id);
    Company findByName(String name);
    Company findByCnpj(String cnpj);

    @Query(value = "select new Oracle.Partner.Tracker.dto.StatePerCompany(c.state, COUNT(c.state) as companyCount) from Company c GROUP BY c.state")
    List<StatePerCompany> getCompaniesByState();

    @Query("SELECT new Oracle.Partner.Tracker.dto.TrackPerCompany(o.name, COUNT(DISTINCT c.id)) FROM Company c " +
            "LEFT JOIN c.companyOpnTrack co " +
            "LEFT JOIN co.opnTrack o " +
            "GROUP BY o.name")
    public List<TrackPerCompany> getTrackPerCompany();

    List<Company> findAllByStatus(Status status);

    @Query(value = "SELECT c.name, c.opnStatus, c.country, c.state, c.city, c.address, c.createAt, c.status, c.site FROM Company c")
    List<Object[]> findAllCompanyAtributes();

}

