package com.maukaim.bulo.mockingbird.connect;

import com.maukaim.bulo.app.endpoints.client.ClientEventType;
import com.maukaim.bulo.app.shared.system.communication.core.rest.RestSystemEventConnector;
import com.maukaim.bulo.mockingbird.marshaller.MockingBirdMarshaller;

import java.util.List;

public class Application {
    private final RestSystemEventConnector<ClientEventType> systemConnector;
    protected final MockingBirdMarshaller marshaller;

    public Application(RestSystemEventConnector<ClientEventType> systemConnector, MockingBirdMarshaller marshaller) {
        this.systemConnector = systemConnector;
        this.marshaller = marshaller;
    }

    protected <RT> List<RT> sendAndGetList(Object body, ClientEventType type, Class<RT> clazz) {
        String result = sendAndGetRaw(body, type);
        return marshaller.unmarshallAsList(result, clazz);
    }

    protected <RT> RT sendAndGet(Object body, ClientEventType type, Class<RT> clazz) {
        String result = sendAndGetRaw(body, type);
        if (!String.class.isAssignableFrom(clazz)) {
            return marshaller.unmarshall(result, clazz);
        }
        return clazz.cast(result);
    }

    protected void sendAndForget(Object body, ClientEventType type) {
        sendAndGetRaw(body, type);
    }

    protected String sendAndGetRaw(Object body, ClientEventType type) {
        List<Object> result = this.systemConnector.sendExternal(body, type);
        if (result.size() != 1) {
            throw new RuntimeException("Expected only 1 result. Seems multiple service or instances has replied: " + result);
        } else {
            return result.iterator().next().toString();
        }
    }
}
