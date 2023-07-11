package uz.utkirbek.springbootcrudwithsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author: utkirbek.
 * Time: 18:36:13
 * Date: July 06, 2023, Thursday
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("uz.utkirbek.springbootcrudwithsecurity.controller"))
                .build();
    }

    /**go to http://localhost:8080/swagger-ui.html after starting the application
    * in order to see the swagger-ui
    */

}
