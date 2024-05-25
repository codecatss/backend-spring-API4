package Oracle.Partner.Tracker.dto;

import jakarta.validation.constraints.NotBlank;

public record ExpertiseRecord(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotBlank
        String statusString
) { }