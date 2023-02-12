package com.maukaim.bulo.ms.shared.spring.servers;

import com.maukaim.bulo.shared.server.core.SystemContext;
import com.maukaim.bulo.app.shared.spring.servers.AbstractSpringEndpointConsumerResolver;
import com.maukaim.bulo.app.shared.system.communication.api.ApplicationMode;
import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;
import com.maukaim.bulo.ms.shared.system.endpoints.ForServiceEventType;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Method;
import java.util.Map;

public class ServiceSpringEndpointConsumerResolver extends AbstractSpringEndpointConsumerResolver<ServiceEventType> {

    public ServiceSpringEndpointConsumerResolver(SystemContext systemContext,
                                                 Map<ServiceEventType, Class<?>> systemEndpointMap) {
        super(systemContext, systemEndpointMap, ApplicationMode.microservices);
    }

    @Override
    protected String getAppendixServicePath(Class<?> systemEndpointClass, ServiceEventType eventType) {
        Method[] methods = systemEndpointClass.getMethods();
        for (Method method : methods) {
            ForServiceEventType forServiceEventTypeAnnotation = method.getAnnotation(ForServiceEventType.class);
            if (forServiceEventTypeAnnotation != null) {
                ServiceEventType systemEventTypeSupported = forServiceEventTypeAnnotation.value();
                if (eventType.equals(systemEventTypeSupported)) {
                    PostMapping postMappingAnnotation = method.getAnnotation(PostMapping.class);
                    if (postMappingAnnotation == null) {
                        throw new RuntimeException("ConsumerFor present, but mot PostMapping. Set up error: " + systemEndpointClass.getName());
                    }
                    String[] possiblePaths = postMappingAnnotation.value();
                    if (possiblePaths.length != 1) {
                        throw new RuntimeException(" PostMapping should have 1 (and only 1) path. None or multiple, not supported.");
                    }
                    return possiblePaths[0];
                }
            }
        }
        throw new RuntimeException(String.format("Event type %s not supported by system endpoint %s",
                eventType, systemEndpointClass.getName()));
    }
}
