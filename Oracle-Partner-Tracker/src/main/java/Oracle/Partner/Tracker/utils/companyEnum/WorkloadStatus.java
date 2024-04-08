package Oracle.Partner.Tracker.utils.companyEnum;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public enum WorkloadStatus {
    ACTIVE("Active"),INACTIVE("Inactive");

    private String status;
}
