package Oracle.Partner.Tracker.utils.companyEnum;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public enum CompanyStatus {
    ACTIVE("ACTIVE"),INACTIVE("INACTIVE");

    private String status;
}
