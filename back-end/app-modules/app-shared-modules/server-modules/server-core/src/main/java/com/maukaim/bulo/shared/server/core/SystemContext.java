package com.maukaim.bulo.shared.server.core;

import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.shared.server.core.model.ApplicationEnvironment;
import com.maukaim.bulo.shared.server.core.model.BuloSystemProperties;
import com.maukaim.bulo.shared.server.core.model.KafkaProperties;
import com.maukaim.bulo.shared.server.core.model.WebServerProperties;

import java.util.List;
import java.util.Map;

public class SystemContext {
    private final ApplicationEnvironment environment;
    private final BuloSystemProperties buloSystemProperties;
    private final ServiceName serviceName;

    public SystemContext(ServiceName serviceName,
                         ApplicationEnvironment environment,
                         BuloSystemProperties buloSystemProperties) {
        this.serviceName = serviceName;
        this.environment = environment;
        this.buloSystemProperties = buloSystemProperties;
    }

    public ServiceName getServiceName() {
        return serviceName;
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

    public KafkaProperties getKafkaBrokers() {
        return buloSystemProperties.getKafkaPropertiesMap().get(environment);
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
