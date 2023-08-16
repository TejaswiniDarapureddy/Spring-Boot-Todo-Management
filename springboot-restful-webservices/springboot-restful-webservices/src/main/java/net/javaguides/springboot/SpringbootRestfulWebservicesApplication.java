package net.javaguides.springboot;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//Main entry class is basically a configuration class, we need this to annotate with swagger annotation.
//It is a swagger annotation defined from swagger package
@OpenAPIDefinition(
		//This annotation has attribute info which helps us to use @Info annotation that provides the
		//API information. //This is basically defines the general information of documentation
		info = @Info(
				title = "Spring Boot REST API Documentation",
				description = "Spring Boot Rest API documentation",
				version = "v1.0", //gives the version of the documentation
				//We have to update the contact and license details as per our requirement
				contact = @Contact(
						name = "Satya",
						email = "satya@gmail.com",
						url = "https://javaguides.net"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://javaguides.net/license"
				)
		),
		//We can also define the external documentation details.
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot User Management Documentation",
				url = "https://javaguides.net/user_management"
		)

)
public class SpringbootRestfulWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

}
