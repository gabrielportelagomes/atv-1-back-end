package br.com.dev.show.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Alfa - Documentação Oficial")
                        .version("v1.0.0")
                        .description("API REST com arquitetura em camadas, validação de regras de negócio e tratamento global de exceções.")
                        .contact(new Contact()
                                .name("Time de Desenvolvimento")
                                .email("dev@alfa.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")));
    }
}

