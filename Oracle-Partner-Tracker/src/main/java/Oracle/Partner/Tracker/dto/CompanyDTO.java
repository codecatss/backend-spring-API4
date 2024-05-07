package Oracle.Partner.Tracker.dto;

import com.opencsv.bean.CsvBindByName;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.utils.OPNStatus;
import Oracle.Partner.Tracker.utils.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CompanyDTO implements GenericDTO{

    @Schema(description = "ID da empresa", example = "1")
    private Long id;

    @CsvBindByName(column = "Company Name")
    @Schema(description = "Nome da empresa", example = "Empresa XYZ")
    private String name;

    @CsvBindByName(column = "Company Slogan")
    @Schema(description = "Slogan da empresa", example = "Fazemos a diferença!")
    private String slogan;

    @CsvBindByName(column = "Company OPN Status")
    private String opnStatusString;

    @Schema(description = "Status da OPN (Oracle Partner Network) da empresa", example = "true")
    private OPNStatus opnStatus;

    @CsvBindByName(column = "Company CNPJ")
    @Schema(description = "CNPJ da empresa", example = "12345678901234")
    private String cnpj;

    @CsvBindByName(column = "Company Country")
    @Schema(description = "País da empresa", example = "Brasil")
    private String country;

    @CsvBindByName(column = "Company State")
    @Schema(description = "Estado da empresa", example = "São Paulo")
    private String state;

    @CsvBindByName(column = "Company City")
    @Schema(description = "Cidade da empresa", example = "São Paulo")
    private String city;

    @CsvBindByName(column = "Company Address")
    @Schema(description = "Endereço da empresa", example = "Av. Paulista, 123")
    private String address;

    @CsvBindByName(column = "Company Status")
    private String statusString;

    @Schema(description = "Status do company", example = "true")
    private Status status;

    @CsvBindByName(column = "Company Credit Hold")
    @Schema(description = "Status de crédito da empresa", example = "true")
    private String creditHold;

    @Schema(description = "Data de criação do company", example = "2022-01-01T12:00:00")
    private LocalDateTime createAt;

    @Schema(description = "Data de atualização do company", example = "2022-01-01T12:00:00")
    private LocalDateTime updateAt;

    @Schema(description = "Operação de ingestão do company", example = "MANUAL")
    IngestionOperation ingestionOperation;;

    public CompanyDTO() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.ingestionOperation = IngestionOperation.CSV;
    }

    public CompanyDTO(Company entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.opnStatus = entity.getOpnStatus();
        this.cnpj = entity.getCnpj();
        this.country = entity.getCountry();
        this.state = entity.getState();
        this.city = entity.getCity();
        this.address = entity.getAddress();
        this.status = entity.getStatus();
        this.createAt = entity.getCreateAt();
        this.updateAt = entity.getUpdateAt();
        this.ingestionOperation = entity.getIngestionOperation();
        this.creditHold = entity.getCreditHold();
        this.slogan = entity.getSlogan();
    }

    public CompanyDTO(String name, String slogan, String opnStatusString, String cnpj, String country, String state, String city, String address, String statusString, String creditHold) {
        this();
        this.name = name;
        this.slogan = slogan;
        this.opnStatus = OPNStatus.valueOf(opnStatusString.trim().toUpperCase());
        this.cnpj = cnpj;
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
        this.status = Status.toStatus(statusString);
        this.creditHold = creditHold;
        this.ingestionOperation = IngestionOperation.MANUAL;
    }

    public void setOpnStatusString(String opnStatusString) {
        this.opnStatusString = opnStatusString;
        this.opnStatus = OPNStatus.valueOf(opnStatusString.trim().toUpperCase());
    }

    public void setStatusString(String statusString){
        this.status = Status.toStatus(statusString);
    }

}
