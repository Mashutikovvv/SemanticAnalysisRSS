package mashutikov.semanticAnalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SemanticAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SemanticAnalysisApplication.class, args);
	}

}
