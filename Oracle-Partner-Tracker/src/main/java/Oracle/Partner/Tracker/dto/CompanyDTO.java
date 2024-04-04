package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.entities.Company;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Schema(description = "Status de crédito da empresa", example = "true")
    private String creditHold;

    @Schema(description = "Status da empresa", example = "true")
    private Boolean companyStatus;

    @Schema(description = "Slogan da empresa", example = "Fazemos a diferença!")
    private String slogan;

    public CompanyDTO(Company entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.opnStatus = entity.getOpnStatus();
        this.cnpj = entity.getCnpj();
        this.country = entity.getCountry();
        this.state = entity.getState();
        this.city = entity.getCity();
        this.address = entity.getAddress();
        this.cep = entity.getCep();
        this.createOn = entity.getCreateOn();
        this.creditHold = entity.getCreditHold();
        this.companyStatus = entity.getCompanyStatus();
        this.slogan = entity.getSlogan();
    }
}
