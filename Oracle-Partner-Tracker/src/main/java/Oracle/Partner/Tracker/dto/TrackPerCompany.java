package Oracle.Partner.Tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackPerCompany implements GenericDTO{
    private String name;
    private Long quantity;

    @Override
    public String getValueByName(String name) {
        return switch (name) {
            case "title" -> this.name;
            case "subtitle" -> "Not Applicable";
            case "amount" -> quantity.toString();
            case "avatarIcon" -> "bx-bar-chart-alt-2";
            default -> null;
        };
    }
}
