package uy.com.minza.mutantes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import uy.com.minza.mutantes.MutantsApplication;

import javax.servlet.ServletContext;
import java.util.ArrayList;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/**
 * Configuración de Swagger UI.
 *
 * @author Martin Inzaurralde - eminzaurralde@gmail.com
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Build Properties del proyecto.
     */
    private BuildProperties buildProperties;

    /**
     * Crea una nueva instancia.
     *
     * @param buildProperties Build properties que se va a utilizar.
     */
    public SwaggerConfig(
            @Autowired BuildProperties buildProperties
    ) {
        this.buildProperties = buildProperties;
    }

    /**
     * Genera el docklet con la descripción de la API.
     *
     * @param servletContext ServletContext.
     * @return El docklet con la información de la API para Swagger.
     */
    @Bean
    public Docket api(final ServletContext servletContext) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage(MutantsApplication.class.getPackage().getName()))
                .build()
                .apiInfo(metadata())
                .useDefaultResponseMessages(false);
    }

    /**
     * Obtiene la metadata de la API.
     *
     * @return Un objeto ApiInfo con la metatada de la API.
     */
    private ApiInfo metadata() {
        return new ApiInfo(
                "Mutant API",
                "API REST para la revisión de ADNs de humanos mutantes",
                buildProperties.getVersion(),
                "",
                new Contact("", "", ""),
                "",
                "",
                new ArrayList<>());
    }
}
