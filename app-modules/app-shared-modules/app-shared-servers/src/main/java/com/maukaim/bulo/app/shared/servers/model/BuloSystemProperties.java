package com.maukaim.bulo.app.shared.servers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;

import java.util.Map;

public class BuloSystemProperties {
    private final String license;
    private final Map<ServiceName, WebServerProperties> servicesMap;

    public BuloSystemProperties(@JsonProperty("services") Map<ServiceName, WebServerProperties> servicesMap,
                                @JsonProperty("license") String license) {

        this.license = license;
        this.servicesMap = Map.copyOf(servicesMap);
    }

    public String getLicense() {
        return license;
    }

    public Map<ServiceName, WebServerProperties> getServicesMap() {
        return servicesMap;
    }
}
