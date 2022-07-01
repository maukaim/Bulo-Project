package com.maukaim.bulo.commons.swagger.config;

import springfox.documentation.spring.web.plugins.Docket;

@FunctionalInterface
public interface DocketCustomizer {

    /**
     *  Allow to customize the docket before it is returned.
     *
     * @param docket , the docket to customize.
     */
    void customize(Docket docket);
}
