package Oracle.Partner.Tracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class DashboardDTO {
    private Integer qtyPartners;
    private Integer qtyPartnersActive;
    private Integer qtyPartnersInactive;
    private BigDecimal averageTracksPerPartners;
    private Integer qtyUsers;
    private Integer qtyTracks;
    private Integer qtyExpertise;
}