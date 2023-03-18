package com.maukaim.bulo.app.shared.systen.communication.rest;

import com.maukaim.bulo.app.shared.system.communication.api.SystemEventType;
import com.maukaim.bulo.app.shared.system.communication.core.StdSystemConnector;
import com.maukaim.bulo.app.shared.system.communication.core.SystemEventConsumerResolver;
import com.maukaim.bulo.marshalling.Marshaller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.ConnectIOException;
import java.util.ArrayList;
import java.util.List;

public class RestSystemEventConnector<TYPE extends SystemEventType> extends StdSystemConnector<RestSystemEventSender, TYPE> {
    private final HttpClient httpClient;
    private final Marshaller marshaller;

    public RestSystemEventConnector(HttpClient httpClient,
                                    SystemEventConsumerResolver<RestSystemEventSender, TYPE> systemEventConsumerResolver,
                                    Marshaller marshaller,
                                    boolean isStrictMode) {
        super(systemEventConsumerResolver, isStrictMode);
        this.httpClient = httpClient;
        this.marshaller = marshaller;
    }

    @Override
    protected List<Object> send(Object event, RestSystemEventSender consumer) throws ConnectIOException {
        String eventAsJson = marshaller.marshall(event);
        List<String> potentialUris = consumer.getPotentialUris();

        List<ConnectIOException> stackedExceptions = new ArrayList<>();
        List<Object> results = new ArrayList<>();
        for (String uri : potentialUris) {
            HttpResponse<String> response;
            try {
                response = httpClient.send(resolveRequest(eventAsJson, uri), HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                stackedExceptions.add(new ConnectIOException("IO issue when connecting Uri: " + uri, e));
                continue;
            } catch (InterruptedException e) {
                stackedExceptions.add(new ConnectIOException("Interruption received while connecting Uri: " + uri, e));
                continue;
            }
            if (response.statusCode() == 200) {
                results.add(response.body());
                break;
            } else {
                stackedExceptions.add(new ConnectIOException(String.format("Problem with uri %s, status code is %s. Hint Message:%s",
                        uri, response.statusCode(), response.body()))
                );
            }
        }
        if (!stackedExceptions.isEmpty()) {
            ConnectIOException mainException = new ConnectIOException("Impossible to contact consumer: " + consumer.getIdentifier());
            stackedExceptions.forEach(mainException::addSuppressed);
            throw mainException;
        }
        return results;
    }

    private HttpRequest resolveRequest(String body, String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .build();
    }
}
