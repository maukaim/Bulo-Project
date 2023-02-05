package com.maukaim.bulo.app.shared.servers;

import com.maukaim.bulo.app.shared.servers.model.ApplicationEnvironment;
import com.maukaim.bulo.app.shared.servers.model.BuloSystemProperties;
import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.servers.model.WebServerProperties;

import java.util.List;
import java.util.Map;

public class SystemContext {
    private ApplicationEnvironment environment;
    private BuloSystemProperties buloSystemProperties;

    public SystemContext(ApplicationEnvironment environment, BuloSystemProperties buloSystemProperties) {
        this.environment = environment;
        this.buloSystemProperties = buloSystemProperties;
    }

    public ApplicationEnvironment getEnvironment() {
        return environment;
    }

    public String getBuloLicense() {
        return buloSystemProperties.getLicense();
    }

    public List<String> getHosts(ServiceName serviceName) {
        WebServerProperties webServerProperties = getWebServerProperties(serviceName);
        return webServerProperties.getDnsProperties().getHosts(environment);
    }

    private WebServerProperties getWebServerProperties(ServiceName serviceName) {
        Map<ServiceName, WebServerProperties> serviceMap = getServiceMap();
        if (!serviceMap.containsKey(serviceName)) {
            throw new RuntimeException("The service requested does not have WebServerProperties declared: " + serviceName);
        }
        return serviceMap.get(serviceName);
    }

    private Map<ServiceName, WebServerProperties> getServiceMap() {
        return buloSystemProperties.getServicesMap();
    }

    public String getPort(ServiceName serviceName) {
        WebServerProperties webServerProperties = getWebServerProperties(serviceName);
        return String.valueOf(webServerProperties.getPort());
    }
}
