package br.com.erudio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableWebMvc
@EnableSwagger
public class SwaggerConfig {

	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;
	
	@Bean
	public SwaggerSpringMvcPlugin groupOnePlugin() {
		return new SwaggerSpringMvcPlugin(springSwaggerConfig).
				apiInfo(apiInfo()).
				includePatterns("/api/.*").
				swaggerGroup("erudio");
	}

	private ApiInfo apiInfo() {
		
		ApiInfo apiInfo = new ApiInfo("ERUDIO API",
				"ERUDIO API's connecting world",
				"ERUDIO API's terms of service",
				"erudio_api@gmail.com",
				"ERUDIO API",
				"http://erudio.com.br");
		return apiInfo;
	}
}