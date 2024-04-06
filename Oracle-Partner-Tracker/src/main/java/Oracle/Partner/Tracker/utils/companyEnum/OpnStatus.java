package Oracle.Partner.Tracker.utils.companyEnum;


import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public enum OpnStatus {

    ACTIVE("Active"),INACTIVE("Inactive"),EXPIRED("Expired");


    private String status;


}
