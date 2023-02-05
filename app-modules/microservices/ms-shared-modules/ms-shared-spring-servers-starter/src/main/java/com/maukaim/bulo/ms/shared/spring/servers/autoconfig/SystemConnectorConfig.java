package com.maukaim.bulo.ms.shared.spring.servers.autoconfig;

import com.maukaim.bulo.app.shared.servers.SystemContext;
import com.maukaim.bulo.app.shared.spring.servers.SystemAwareConfig;
import com.maukaim.bulo.marshalling.Marshaller;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.app.shared.system.communication.core.SystemEventConsumerResolver;
import com.maukaim.bulo.app.shared.system.communication.core.rest.RestSystemEventConnector;
import com.maukaim.bulo.app.shared.system.communication.core.rest.RestSystemEventConsumer;
import com.maukaim.bulo.ms.shared.spring.servers.ServiceSpringEndpointConsumerResolver;
import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;
import com.maukaim.bulo.ms.shared.system.endpoints.SystemEndpointProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
@AutoConfigureBefore(SystemAwareConfig.class)
@AutoConfigureAfter(ApplicationMarshallerConfig.class)
public class SystemConnectorConfig {
    @Bean
    @ConditionalOnMissingBean(SystemConnector.class)
    public SystemConnector<ServiceEventType> systemConnector(Marshaller marshaller,
                                           SystemEventConsumerResolver<RestSystemEventConsumer, ServiceEventType> systemConsumerResolver) {
        return new RestSystemEventConnector<>(HttpClient.newHttpClient(), systemConsumerResolver, marshaller, false);
    }

    @Bean
    public SystemEventConsumerResolver<RestSystemEventConsumer, ServiceEventType> restSystemEventConsumerResolver(SystemContext systemContext) {
        return new ServiceSpringEndpointConsumerResolver(systemContext, SystemEndpointProvider.getSystemEndpointClasses());

    }
}


