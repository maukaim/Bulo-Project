package com.maukaim.bulo.app.shared.servers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PropertiesMapper extends ObjectMapper {
    private static final long serialVersionUID = -8124692119951754873L;

    private PropertiesMapper() {
        super();
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        this.setVisibility(this.getSerializationConfig().getDefaultVisibilityChecker()
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY));
    }

    public static PropertiesMapper instance(){
        return new PropertiesMapper();
    }
}
