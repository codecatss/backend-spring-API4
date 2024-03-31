package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.entities.Company;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class CompanyDTO {

    @Schema(description = "ID da empresa", example = "1")
    private String id;

    @Schema(description = "Nome da empresa", example = "Empresa XYZ")
    private String name;

    @Schema(description = "Status da OPN (Oracle Partner Network) da empresa", example = "true")
    private Boolean opnStatus;

    @Schema(description = "CNPJ da empresa", example = "12345678901234")
    private String cnpj;

    @Schema(description = "País da empresa", example = "Brasil")
    private String country;

    @Schema(description = "Estado da empresa", example = "São Paulo")
    private String state;

    @Schema(description = "Cidade da empresa", example = "São Paulo")
    private String city;

    @Schema(description = "Endereço da empresa", example = "Av. Paulista, 123")
    private String address;

    @Schema(description = "CEP da empresa", example = "12345-678")
    private String cep;

    @Schema(description = "Data de criação da empresa", example = "2022-01-01T12:00:00")
    private LocalDateTime createOn;



    public CompanyDTO(Company entity){
        id = entity.getId();
        name = entity.getName();
        opnStatus = entity.getOpnStatus();
        cnpj = entity.getCnpj();
        country = entity.getCountry();
        state = entity.getState();
        city = entity.getCity();
        address = entity.getAddress();
        cep = entity.getCep();
        createOn = entity.getCreateOn();
    }


}
