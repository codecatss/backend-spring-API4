package Oracle.Partner.Tracker.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(

    @NotBlank
    String email,

    @NotBlank
    String password
) {}
