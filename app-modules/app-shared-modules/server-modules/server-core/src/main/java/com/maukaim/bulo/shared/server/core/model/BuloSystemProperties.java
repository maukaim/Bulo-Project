package com.maukaim.bulo.shared.server.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;

import java.util.Map;

public class BuloSystemProperties {
    private final String license;
    private final Map<ServiceName, WebServerProperties> servicesMap;
    private final Map<ApplicationEnvironment, KafkaProperties> kafkaPropertiesMap;

    public BuloSystemProperties(@JsonProperty("services") Map<ServiceName, WebServerProperties> servicesMap,
                                @JsonProperty("kafka") Map<ApplicationEnvironment, KafkaProperties> kafkaPropertiesMap,
                                @JsonProperty("license") String license) {

        this.license = license;
        this.kafkaPropertiesMap = kafkaPropertiesMap;
        this.servicesMap = Map.copyOf(servicesMap);
    }

    public String getLicense() {
        return license;
    }

    public Map<ServiceName, WebServerProperties> getServicesMap() {
        return servicesMap;
    }

    public Map<ApplicationEnvironment, KafkaProperties> getKafkaPropertiesMap() {
        return kafkaPropertiesMap;
    }
}
