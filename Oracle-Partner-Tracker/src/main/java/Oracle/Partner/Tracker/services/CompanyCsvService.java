package Oracle.Partner.Tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import Oracle.Partner.Tracker.dto.CompanyDTO;

@Service
public class CompanyCsvService extends CsvService<CompanyDTO> {

    @Autowired
    public CompanyCsvService(@Qualifier("companyService") GenericService<CompanyDTO> service) {
        super(service);
    }
}
