package Oracle.Partner.Tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import Oracle.Partner.Tracker.dto.OpnTrackDTO;

@Service
public class OpnTrackCsvService extends CsvService<OpnTrackDTO> {

    @Autowired
    public OpnTrackCsvService(@Qualifier("opnTrackService") GenericService<OpnTrackDTO> service) {
        super(service);
    }
}