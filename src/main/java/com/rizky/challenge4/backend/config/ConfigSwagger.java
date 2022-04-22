package com.rizky.challenge4.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSwagger {

    @Bean
    public OpenAPI customOpenAPI(@Value("Documentation API's for Cinematic bioskop with operations CRUD for film, user, schedule and generate invoice ticketing to pdf") String appDescription,
                                 @Value("v1.2.0") String appVersion) {
        return new OpenAPI().info(
                new Info().title("Cinematic")
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService("http://swagger.io/terms")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdocs.org"))
                        .contact(new Contact()
                                .name("Rizky Abdul Jabar")
                                .email("rizkyJabar@gmail.com"))
        );
    }

}
