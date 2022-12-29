package com.alticci.sequence.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SwaggerConfig {

    @Bean
    OpenAPI springOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                .title("Alticci Sequence")
                .description("Alticci sequence project - Swagger")
                .version("v0.0.1"));
    }

}