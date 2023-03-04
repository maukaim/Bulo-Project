package com.maukaim.bulo.app.shared.spring.servers;

import com.maukaim.bulo.shared.server.core.SystemContext;
import com.maukaim.bulo.app.shared.system.communication.api.ApplicationMode;
import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEventType;
import com.maukaim.bulo.app.shared.system.communication.core.SystemEventConsumerResolver;
import com.maukaim.bulo.app.shared.system.communication.core.rest.RestSystemEventConsumer;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractSpringEndpointConsumerResolver<TYPE extends SystemEventType> implements SystemEventConsumerResolver<RestSystemEventConsumer, TYPE> {
    private final SystemContext systemContext;
    private final Map<TYPE, Class<?>> systemEndpointMap;
    private final ApplicationMode applicationMode;

    public AbstractSpringEndpointConsumerResolver(SystemContext systemContext,
                                                  Map<TYPE, Class<?>> systemEndpointMap,
                                                  ApplicationMode applicationMode) {
        this.systemContext = systemContext;
        this.systemEndpointMap = systemEndpointMap;
        this.applicationMode = applicationMode;
    }

    abstract protected String getAppendixServicePath(Class<?> systemEndpointClass, TYPE eventType);

    @Override
    public List<RestSystemEventConsumer> resolve(TYPE eventType) {
        Map<ServiceName, String> serviceNamesToServicePath = resolveConsumersServicePath(eventType);

        return serviceNamesToServicePath.keySet().stream()
                .map(serviceName -> {
                    List<String> potentialHosts = systemContext.getHosts(serviceName);
                    String port = systemContext.getPort(serviceName);
                    String servicePath = serviceNamesToServicePath.get(serviceName);
                    return new RestSystemEventConsumer(serviceName, potentialHosts, port, servicePath);
                }).toList();
    }

    private Map<ServiceName, String> resolveConsumersServicePath(TYPE eventType) {
        if (!this.systemEndpointMap.containsKey(eventType)) {
            throw new RuntimeException("EventType is not mapped to any SystemEndpoint: " + eventType);
        }
        Class<?> systemEndpoint = this.systemEndpointMap.get(eventType);
        String rootServicePath = getRootServicePath(systemEndpoint);
        String appendixServicePath = getAppendixServicePath(systemEndpoint, eventType);
        List<ServiceName> servicesExpectedToListen = getServiceNames(systemEndpoint);

        return servicesExpectedToListen.stream()
                .collect(Collectors.toMap(
                        serviceName -> serviceName,
                        serviceName -> String.format("%s%s", rootServicePath, appendixServicePath)
                ));
    }

    private List<ServiceName> getServiceNames(Class<?> systemEndpointClass) {
        SystemEndpointExpectedIn endpointExpectedInAnnotation = systemEndpointClass.getAnnotation(SystemEndpointExpectedIn.class);
        if (endpointExpectedInAnnotation == null) {
            throw new RuntimeException("Annotation EndpointExpectedIn is missing, can't find service names");
        }
        return Arrays.stream(endpointExpectedInAnnotation.value())
                .filter(serviceName -> serviceName.getApplicationMode() == applicationMode)
                .collect(Collectors.toList());
    }

    private String getRootServicePath(Class<?> systemEndpointClass) {
        RequestMapping requestMappingAnnotation = systemEndpointClass.getAnnotation(RequestMapping.class);
        if (requestMappingAnnotation == null) {
            throw new RuntimeException("Annotation RequestMapping is missing, can't find root service path");
        }

        String[] allPossiblePath = requestMappingAnnotation.value();
        if (allPossiblePath.length != 1) {
            throw new RuntimeException(" RequestMapping should have 1 (and only 1) path. None or multiple, not supported.");
        }
        return allPossiblePath[0];
    }
}
