package com.cbnits;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Employee Management REST API Documentation",
				version = "v1",
				description = "Employee Management REST API Documentation",
				contact = @Contact(
						name = "Tapajyoti Ghosh",
						email = "developer@cbnits.com",
						url= "https://cbnits.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "CBNITS Documentation",
				url = "https://docs.cbnits.com"
		)
)
public class CbnitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbnitsApplication.class, args);
	}

}
