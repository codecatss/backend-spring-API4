package Oracle.Partner.Tracker.dto;

import jakarta.validation.constraints.NotBlank;

public record CompanyRecord(

        @NotBlank
        String name,

        @NotBlank
        String cnpj,

        @NotBlank
        String city,

        @NotBlank
        String address,

        @NotBlank
        String state,
        
        String site
) {}