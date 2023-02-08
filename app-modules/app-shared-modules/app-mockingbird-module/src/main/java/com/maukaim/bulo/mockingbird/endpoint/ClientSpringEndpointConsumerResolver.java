package com.maukaim.bulo.mockingbird.endpoint;

import com.maukaim.bulo.app.commons.endpoints.ClientEventType;
import com.maukaim.bulo.app.commons.endpoints.ForClientEventType;
import com.maukaim.bulo.app.shared.servers.SystemContext;
import com.maukaim.bulo.app.shared.spring.servers.AbstractSpringEndpointConsumerResolver;
import com.maukaim.bulo.app.shared.system.communication.api.ApplicationMode;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Method;
import java.util.Map;

public class ClientSpringEndpointConsumerResolver extends AbstractSpringEndpointConsumerResolver<ClientEventType> {

    public ClientSpringEndpointConsumerResolver(SystemContext systemContext,
                                                Map<ClientEventType, Class<?>> systemEndpointMap,
                                                ApplicationMode applicationMode) {
        super(systemContext, systemEndpointMap, applicationMode);
    }

    @Override
    protected String getAppendixServicePath(Class<?> systemEndpointClass, ClientEventType eventType) {
        Method[] methods = systemEndpointClass.getMethods();
        for (Method method : methods) {
            ForClientEventType forServiceEventTypeAnnotation = method.getAnnotation(ForClientEventType.class);
            if (forServiceEventTypeAnnotation != null) {
                ClientEventType systemEventTypeSupported = forServiceEventTypeAnnotation.value();
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