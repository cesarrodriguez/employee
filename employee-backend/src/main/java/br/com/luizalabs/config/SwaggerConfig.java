package br.com.luizalabs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * That class is to used by Swagger Framework to configure "Rest" documentation.
 * 
 * Please see the link class for true identity
 * 
 * @author Iron Man
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * <p>
	 * This is a bean that handling information of the all services
	 * 
	 * </p>
	 * 
	 * @return whole the documentation of services(api).
	 * @see <a href="link_to_ticket_repository">ticket_123</a>
	 * @since 1.0
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.luizalabs.controller")).paths(PathSelectors.any())
				.build().apiInfo(metaData());
	}

	/**
	 * <p>
	 * This is a method that handling header information of Swagger
	 * 
	 * </p>
	 * 
	 * @return the header of Swagger
	 * @see <a href="link_to_ticket_repository">ticket_123</a>
	 * @since 1.0
	 */
	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("LUIZA LABS REST API")
				.description("\"Spring Boot REST API for Employee management\"").version("1.0.0")
				.license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.build();
	}

}
