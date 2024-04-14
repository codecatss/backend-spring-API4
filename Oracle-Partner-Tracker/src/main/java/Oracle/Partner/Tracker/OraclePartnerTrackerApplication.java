package Oracle.Partner.Tracker;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

@OpenAPIDefinition(
		info = @Info(
				title = "Oracle Partner Network",
				description = "API responsável pela gestão e visualização de progresso de parceiros Oracle",
				version = "1"
		)
)
public class OraclePartnerTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OraclePartnerTrackerApplication.class, args);

		System.out.println("Server is running");

		System.out.println("  /\\_/\\");
		System.out.println(" ( o.o )");
		System.out.println("  > ^ <");
		System.out.println("  MEEOW!");
	}

}
