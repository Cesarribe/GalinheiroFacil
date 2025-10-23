package org.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI galinheiroOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Galinheiro Fácil")
                        .version("1.0")
                        .description("Documentação da API para gerenciamento de lotes de animais"));
    }
}

