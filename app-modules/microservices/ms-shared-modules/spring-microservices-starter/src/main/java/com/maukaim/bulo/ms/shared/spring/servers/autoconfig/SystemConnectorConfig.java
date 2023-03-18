package com.maukaim.bulo.ms.shared.spring.servers.autoconfig;

import com.maukaim.bulo.shared.server.core.SystemContext;
import com.maukaim.bulo.app.shared.spring.servers.SystemAwareConfig;
import com.maukaim.bulo.marshalling.Marshaller;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.app.shared.system.communication.core.SystemEventConsumerResolver;
import com.maukaim.bulo.app.shared.systen.communication.rest.RestSystemEventConnector;
import com.maukaim.bulo.app.shared.systen.communication.rest.RestSystemEventSender;
import com.maukaim.bulo.ms.shared.spring.servers.ServiceSpringRestEndpointConsumerResolver;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
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
    public SystemConnector<MicroServiceEventType> systemConnector(Marshaller marshaller,
                                                                  SystemEventConsumerResolver<RestSystemEventSender, MicroServiceEventType> systemConsumerResolver) {
        return new RestSystemEventConnector<>(HttpClient.newHttpClient(), systemConsumerResolver, marshaller, false);
    }

    @Bean
    public SystemEventConsumerResolver<RestSystemEventSender, MicroServiceEventType> restSystemEventConsumerResolver(SystemContext systemContext) {
        return new ServiceSpringRestEndpointConsumerResolver(systemContext, SystemEndpointProvider.getSystemEndpointClasses());
    }
}


