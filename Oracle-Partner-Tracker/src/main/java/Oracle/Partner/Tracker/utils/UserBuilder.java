package Oracle.Partner.Tracker.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserBuilder {
    private String name;
    private String email;
    private String password;
    private RoleEnum role;
    private Status status;
    private IngestionOperation ingestionOperation;
    private MembershipEnum memberShipType;
}

