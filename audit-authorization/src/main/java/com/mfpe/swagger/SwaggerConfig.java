package com.mfpe.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class for customizing swagger
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Method for configuring swagger
	 * 
	 * @return This returns a Docket object with customized configuration
	 */
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.mfpe"))
				.paths(PathSelectors.any()).build();
	}
}