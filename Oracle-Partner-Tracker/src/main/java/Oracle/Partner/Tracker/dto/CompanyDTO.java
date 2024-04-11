package Oracle.Partner.Tracker.dto;

import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.utils.companyEnum.CompanyStatus;
import Oracle.Partner.Tracker.utils.companyEnum.OpnStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import Oracle.Partner.Tracker.util.Status;
import lombok.NoArgsConstructor;
import Oracle.Partner.Tracker.utils.companyEnum.IngestionOperation;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    @Schema(description = "ID da empresa", example = "1")
    private Long id;

    @Schema(description = "Nome da empresa", example = "Empresa XYZ")
    private String name;

    @Schema(description = "Status da OPN (Oracle Partner Network) da empresa", example = "true")
    private OpnStatus opnStatus;

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

    @Schema(description = "Status do company", example = "true")
    private CompanyStatus companyStatus;

    @Schema(description = "Data de criação do company", example = "2022-01-01T12:00:00")
    private LocalDateTime created_at;

    @Schema(description = "Data de atualização do company", example = "2022-01-01T12:00:00")
    private LocalDateTime updated_at;

    @Schema(name= "ingestion_operation")
    private IngestionOperation ingestionOperation;
    
    @Schema(description = "Status de crédito da empresa", example = "true")
    private String creditHold;


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
        this.companyStatus = entity.getCompanyStatus();
        this.created_at = entity.getCreatedAt();
        this.updated_at = entity.getUpdatedAt();
        this.ingestionOperation = entity.getIngestionOperation();
        this.creditHold = entity.getCreditHold();
        this.slogan = entity.getSlogan();
    }
}
