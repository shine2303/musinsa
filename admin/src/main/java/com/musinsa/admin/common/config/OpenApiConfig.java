package com.musinsa.admin.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(@Value("${app.version}") String appVersion, @Value("${app.description}") String appDescription,
                           @Value("${app.title}") String appTitle) {

        Info info = new Info().version(appVersion).title(appTitle).description(appDescription);
        return new OpenAPI().info(info);
    }
}