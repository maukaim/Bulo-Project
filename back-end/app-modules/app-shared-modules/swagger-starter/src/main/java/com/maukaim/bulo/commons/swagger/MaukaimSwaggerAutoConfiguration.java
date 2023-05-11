package com.maukaim.bulo.commons.swagger;

import com.maukaim.bulo.commons.swagger.config.DocketCustomizer;
import com.maukaim.bulo.commons.swagger.config.MaukaimSwaggerProperties;
import com.maukaim.bulo.commons.swagger.config.SwaggerConfigCustomizer;
import com.maukaim.bulo.commons.swagger.config.springfox.SpringfoxConfigurationProperties;
import com.maukaim.bulo.commons.swagger.config.springfox.SwaggerUiWebFluxConfiguration;
import com.maukaim.bulo.commons.swagger.config.springfox.SwaggerUiWebMvcConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.configuration.OpenApiDocumentationConfiguration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties({
        SpringfoxConfigurationProperties.class,
        MaukaimSwaggerProperties.class
})
@ConditionalOnProperty(prefix = "maukaim.swagger", value = "enabled", havingValue = "true", matchIfMissing = true)
@Import({
        OpenApiDocumentationConfiguration.class,
        SpringDataRestConfiguration.class, // Only one not necessary
        BeanValidatorPluginsConfiguration.class,
        Swagger2DocumentationConfiguration.class,
        SwaggerUiWebFluxConfiguration.class,
        SwaggerUiWebMvcConfiguration.class
})
@AutoConfigureAfter({
        WebMvcAutoConfiguration.class,
        JacksonAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class,
        RepositoryRestMvcAutoConfiguration.class})
public class MaukaimSwaggerAutoConfiguration {
    private final MaukaimSwaggerProperties swaggerProperties;
    private final List<SwaggerConfigCustomizer> configurationCustomizers;
    private final List<DocketCustomizer> docketCustomizers;

    public MaukaimSwaggerAutoConfiguration(@Autowired MaukaimSwaggerProperties swaggerProperties,
                                           @Autowired(required = false) List<SwaggerConfigCustomizer> configurationCustomizers,
                                           @Autowired(required = false) List<DocketCustomizer> docketCustomizers) {
        this.swaggerProperties = swaggerProperties;
        this.configurationCustomizers = configurationCustomizers == null ? new ArrayList<>() : configurationCustomizers;
        this.docketCustomizers = docketCustomizers == null ? new ArrayList<>() : docketCustomizers;
    }

    @PostConstruct
    void customizeConfiguration() {
        this.configurationCustomizers.forEach(customizer -> customizer.customize(this.swaggerProperties));
    }

    @Bean
    public Docket api(ApiInfo apiInfo) {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(Class.class, String.class);
        docketCustomizers.forEach(docketCustomizer -> docketCustomizer.customize(docket));
        return docket;
    }

    @Bean
    public ApiInfo getApiInfo(Contact contact) {
        return new ApiInfo(
                defaultedStringValue(swaggerProperties.getTitle(), "Bulo Swagger - Default title"),
                defaultedStringValue(swaggerProperties.getDescription(), "Bulo service's swagger."),
                defaultedStringValue(swaggerProperties.getVersion(), "VERSION_UNKNOWN"),
                defaultedStringValue(swaggerProperties.getTermOfServiceUrl(), null),
                contact,
                defaultedStringValue(swaggerProperties.getLicense(), "LICENSE_UNKNOWN"),
                defaultedStringValue(swaggerProperties.getLicenseUrl(), null),
                new ArrayList<>());
    }

    @Bean
    public Contact getContact() {
        return new Contact(
                defaultedStringValue(swaggerProperties.getContactName(), "Julien Elkaim"),
                defaultedStringValue(swaggerProperties.getContactUrl(), "maukaim.com"),
                defaultedStringValue(swaggerProperties.getContactEmail(), "julienelk@gmail.com"));
    }

    private String defaultedStringValue(String current, String defaultValue) {
        return current == null || current.isBlank() ? defaultValue : current;
    }
}
