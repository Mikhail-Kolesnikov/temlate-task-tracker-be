package de.upteams.tasktracker.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI taskTrackerOpenAPI() {
        String accessScheme = "accessCookie";
        String refreshScheme = "refreshCookie";

        return new OpenAPI()
                .info(new Info()
                        .title("Task Tracker API")
                        .version("0.0.1")
                        .description("API with JWT in HTTP-only cookies"))
                .addSecurityItem(new SecurityRequirement()
                        .addList(accessScheme)
                        .addList(refreshScheme))
                .components(new Components()
                        .addSecuritySchemes(accessScheme,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.COOKIE)
                                        .name("accessToken")
                                        .description("HTTP-only cookie containing the access JWT"))
                        .addSecuritySchemes(refreshScheme,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.COOKIE)
                                        .name("refreshToken")
                                        .description("HTTP-only cookie containing the refresh JWT"))
                );
    }

}
