package in.belenits.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Product Management API")
                                .version("1.0")
                                .description("CRUD Operations for Products")
                                .contact(
                                        new Contact()
                                                .name("Yogesh")
                                                .email("yogeshtawde9@gmail.com")
                                )
                );
    }
}