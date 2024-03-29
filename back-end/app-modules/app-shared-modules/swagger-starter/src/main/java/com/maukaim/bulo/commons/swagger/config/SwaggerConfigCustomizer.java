package com.maukaim.bulo.commons.swagger.config;

@FunctionalInterface
public interface SwaggerConfigCustomizer {

    /**
     *  Enable to customize the swagger configuration properties provided by the user.
     *
     * @param swaggerProperties , the configuration properties to customize.
     */
    void customize(MaukaimSwaggerProperties swaggerProperties);
}
