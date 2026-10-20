package com.musicapp.crud.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI musicAppOpenAPI() {
        return new OpenAPI().info(new Info()
            .title("Music App CRUD Service")
            .version("1.0")
            .description("CRUD operations for users, catalog, playlists, and history"));
    }
}
