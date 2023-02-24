package com.anjispringbootseries.priceservice.documentation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@OpenAPIDefinition
@Configuration
@Profile({"local", "dev"})
public class OpenApiConfigs {
  @Bean
  public OpenAPI customOpenAPI(
      @Value("${openapi.service.title}") String serviceTitle,
      @Value("${openapi.service.version}") String serviceVersion,
      @Value("${openapi.service.url}") String url) {
    final String securitySchemeName = "bearerAuth";
    return new OpenAPI()
        .servers(List.of(new Server().url(url)))
//        .components(
//            new Components()
//                .addSecuritySchemes(
//                    securitySchemeName,
//                    new SecurityScheme()
//                        .type(SecurityScheme.Type.HTTP)
//                        .scheme("bearer")
//                        .bearerFormat("JWT")))
//        .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
        .info(new Info().title(serviceTitle).version(serviceVersion));
  }
}
