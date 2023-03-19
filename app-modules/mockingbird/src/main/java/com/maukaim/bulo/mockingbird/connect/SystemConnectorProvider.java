package com.maukaim.bulo.mockingbird.connect;

import com.maukaim.bulo.app.endpoints.client.ClientEventType;
import com.maukaim.bulo.shared.server.core.ServerUtils;
import com.maukaim.bulo.shared.server.core.SystemContext;
import com.maukaim.bulo.shared.server.core.model.ApplicationEnvironment;
import com.maukaim.bulo.app.shared.system.communication.api.ApplicationMode;
import com.maukaim.bulo.app.shared.systen.communication.rest.RestSystemEventConnector;
import com.maukaim.bulo.marshalling.Marshaller;
import com.maukaim.bulo.mockingbird.endpoint.ClientEndpointConfig;
import com.maukaim.bulo.mockingbird.endpoint.ClientSpringRestSenderResolver;
import com.maukaim.bulo.mockingbird.marshaller.MarshallerProvider;

import java.net.http.HttpClient;

public class SystemConnectorProvider {

    public static RestSystemEventConnector<ClientEventType> get(ApplicationEnvironment applicationEnvironment, ApplicationMode appMode) {
        SystemContext systemContext = getSystemContext(applicationEnvironment);
        ClientSpringRestSenderResolver consumerResolver = getConsumerResolver(systemContext, appMode);
        Marshaller marshaller = MarshallerProvider.get();
        return new RestSystemEventConnector<>(HttpClient.newHttpClient(), consumerResolver, marshaller, true);
    }

    private static ClientSpringRestSenderResolver getConsumerResolver(SystemContext systemContext, ApplicationMode appMode) {
        return new ClientSpringRestSenderResolver(systemContext, ClientEndpointConfig.getClientEndpointClasses(), appMode);
    }

    private static SystemContext getSystemContext(ApplicationEnvironment applicationEnvironment) {
        resolveAppEnvironment(applicationEnvironment);
        return new SystemContext(ServerUtils.getApplicationEnvironment(), ServerUtils.getBuloSystemProperties());
    }

    private static void resolveAppEnvironment(ApplicationEnvironment applicationEnvironment) {
        ServerUtils.setApplicationEnvironment(applicationEnvironment);
    }
}
