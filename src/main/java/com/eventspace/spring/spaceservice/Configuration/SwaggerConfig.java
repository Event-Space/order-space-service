package com.eventspace.spring.spaceservice.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Event Space")
                        .version("v1")
                        .description("API Documentation for your project"))
                .addServersItem(new Server().url("https://space-event.sdutechnopark.kz/order-service"));
    }
}


