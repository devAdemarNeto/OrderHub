package dev.ademarneto.OrderHub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("OrdeHub API")
                        .description("API para gestão de clientes e pedidos de venda")
                        .contact(new Contact().name("Ademar Neto").email("ademarnetodev@outlook.com"))
                        .version("1.0.0"));
    }

}
