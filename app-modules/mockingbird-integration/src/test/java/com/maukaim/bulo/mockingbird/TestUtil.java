package com.maukaim.bulo.mockingbird;

import com.maukaim.bulo.app.commons.endpoints.ClientEventType;
import com.maukaim.bulo.app.shared.system.communication.api.ApplicationMode;
import com.maukaim.bulo.app.shared.system.communication.core.rest.RestSystemEventConnector;
import com.maukaim.bulo.mockingbird.marshaller.MarshallerProvider;
import com.maukaim.bulo.mockingbird.connect.SystemConnectorProvider;
import com.maukaim.bulo.mockingbird.connect.Application;

import static com.maukaim.bulo.shared.server.core.model.ApplicationEnvironment.dev;

public class TestUtil {

    public static Application getUatApp(ApplicationMode appMode) {
        RestSystemEventConnector<ClientEventType> connector = SystemConnectorProvider.get(dev, appMode);
        return new Application(connector, MarshallerProvider.get());
    }

    public static Application getDevApp(ApplicationMode appMode) {
        RestSystemEventConnector<ClientEventType> connector = SystemConnectorProvider.get(dev, appMode);
        return new Application(connector, MarshallerProvider.get());
    }
}
