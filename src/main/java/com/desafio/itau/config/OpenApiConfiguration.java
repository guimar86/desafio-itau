package com.desafio.itau.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI defineOpenAPI() {

        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Localhost");

        Contact contact = new Contact();
        contact.setName("Renato Martins");
        contact.setEmail("guillenmartins@gmail.com");

        Info information = new Info()
                .title("Desafio - ITAú UniBanco")
                .contact(contact)
                .version("1.0.0")
                .description("Api for ITAú UniBanco");


        return new OpenAPI().info(information).servers(List.of(server));
    }

    @Bean
    public GroupedOpenApi v1Api() {
        return GroupedOpenApi.builder().group("v1").pathsToMatch("/api/v1/**").build();
    }

    @Bean
    public GroupedOpenApi v2Api() {
        return GroupedOpenApi.builder().group("v2").pathsToMatch("/api/v2/**").build();
    }
}
