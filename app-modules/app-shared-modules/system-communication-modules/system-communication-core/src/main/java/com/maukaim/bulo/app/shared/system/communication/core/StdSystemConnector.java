package com.maukaim.bulo.app.shared.system.communication.core;

import com.maukaim.bulo.app.shared.system.communication.api.SystemEventType;

import java.rmi.ConnectIOException;
import java.util.ArrayList;
import java.util.List;

public abstract class StdSystemConnector<T extends SystemEventSender, TYPE extends SystemEventType>
        implements SystemConnector<TYPE> {
    protected final SystemEventConsumerResolver<T, TYPE> systemEventSenderResolver;
    protected final boolean isStrictMode;

    public StdSystemConnector(SystemEventConsumerResolver<T, TYPE> systemEventSenderResolver,
                              boolean isStrictMode) {
        this.isStrictMode = isStrictMode;
        this.systemEventSenderResolver = systemEventSenderResolver;
    }

    protected abstract List<Object> send(Object event, T consumer) throws ConnectIOException;

    @Override
    public List<Object> sendExternal(Object event, TYPE type) {
        List<T> consumers = this.systemEventSenderResolver.resolve(type);
        List<Object> results = new ArrayList<>();
        for (T consumer : consumers) {
            try {
                List<Object> resultsForConsumer = this.send(event, consumer);
                results.addAll(resultsForConsumer);
            } catch (Exception e) {
                if (isStrictMode) {
                    throw new MessageTransmissionException(e);
                } else {
                    System.out.println(">>> Problem while contacting consumer " + consumer.getIdentifier() + " reason is : " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return results;
    }
}
