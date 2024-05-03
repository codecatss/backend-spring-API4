package Oracle.Partner.Tracker.utils;

import Oracle.Partner.Tracker.entities.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserBuilder {
    private String name;
    private Company company;
    private String email;
    private String password;
    private RoleEnum role;
    private Status status;
    private IngestionOperation ingestionOperation;
    private MembershipEnum memberShipType;
}

