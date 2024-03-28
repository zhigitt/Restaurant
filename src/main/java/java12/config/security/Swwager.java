package java12.config.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class Swwager {
    private static final String  API_KEY = "Bearer Token";
    @Bean
    public OpenAPI customOpenAPI(){

        return  new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(API_KEY, apiKeySecurityScheme()))
                .info(new Info().title("Restaurant").description("Java 12"))

                .security(Collections.singletonList(new SecurityRequirement().addList(API_KEY)));
    }
    public SecurityScheme apiKeySecurityScheme(){
        return new SecurityScheme()
                .name("AUTH API")
                .description("Please put the token")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer");

    }
}
