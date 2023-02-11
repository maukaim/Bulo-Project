package com.maukaim.bulo.mockingbird.connect;

import com.maukaim.bulo.app.commons.endpoints.ClientEventType;
import com.maukaim.bulo.app.shared.servers.ServerUtils;
import com.maukaim.bulo.app.shared.servers.SystemContext;
import com.maukaim.bulo.app.shared.servers.model.ApplicationEnvironment;
import com.maukaim.bulo.app.shared.system.communication.api.ApplicationMode;
import com.maukaim.bulo.app.shared.system.communication.core.rest.RestSystemEventConnector;
import com.maukaim.bulo.marshalling.Marshaller;
import com.maukaim.bulo.mockingbird.endpoint.ClientEndpointConfig;
import com.maukaim.bulo.mockingbird.endpoint.ClientSpringEndpointConsumerResolver;
import com.maukaim.bulo.mockingbird.marshaller.MarshallerProvider;

import java.net.http.HttpClient;

public class SystemConnectorProvider {

    public static RestSystemEventConnector<ClientEventType> get(ApplicationEnvironment applicationEnvironment, ApplicationMode appMode) {
        SystemContext systemContext = getSystemContext(applicationEnvironment);
        ClientSpringEndpointConsumerResolver consumerResolver = getConsumerResolver(systemContext, appMode);
        Marshaller marshaller = MarshallerProvider.get();
        return new RestSystemEventConnector<>(HttpClient.newHttpClient(), consumerResolver, marshaller, true);
    }

    private static ClientSpringEndpointConsumerResolver getConsumerResolver(SystemContext systemContext, ApplicationMode appMode) {
        return new ClientSpringEndpointConsumerResolver(systemContext, ClientEndpointConfig.getClientEndpointClasses(), appMode);
    }

    private static SystemContext getSystemContext(ApplicationEnvironment applicationEnvironment) {
        resolveAppEnvironment(applicationEnvironment);
        return new SystemContext(ServerUtils.getApplicationEnvironment(), ServerUtils.getBuloSystemProperties());
    }

    private static void resolveAppEnvironment(ApplicationEnvironment applicationEnvironment) {
        ServerUtils.setApplicationEnvironment(applicationEnvironment);
    }
}
