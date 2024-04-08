package Oracle.Partner.Tracker.utils.companyEnum;


import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public enum OpnStatus {

    ACTIVE("MEMBER"),INACTIVE("INACTIVE"),EXPIRED("EXPIRED");


    private String status;


}
