package Oracle.Partner.Tracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatePerCompany {
    private String state;
    private Integer companyCount;

}
