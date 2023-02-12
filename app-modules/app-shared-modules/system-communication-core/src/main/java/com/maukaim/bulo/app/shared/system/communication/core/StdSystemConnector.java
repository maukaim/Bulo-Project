package com.maukaim.bulo.app.shared.system.communication.core;

import com.maukaim.bulo.app.shared.system.communication.api.SystemEventType;

import java.util.ArrayList;
import java.util.List;

public abstract class StdSystemConnector<T extends SystemEventConsumer, TYPE extends SystemEventType>
        implements SystemConnector<TYPE> {
    protected final SystemEventConsumerResolver<T, TYPE> systemEventConsumerResolver;
    protected final boolean isStrictMode;

    public StdSystemConnector(SystemEventConsumerResolver<T, TYPE> systemEventConsumerResolver,
                              boolean isStrictMode) {
        this.isStrictMode = isStrictMode;
        this.systemEventConsumerResolver = systemEventConsumerResolver;
    }

    protected abstract List<Object> sendToConsumer(Object event, T consumer) throws Exception;

    @Override
    public List<Object> sendExternal(Object event, TYPE type) {
        List<T> consumers = this.systemEventConsumerResolver.resolve(type);
        List<Object> results = new ArrayList<>();
        for (T consumer : consumers) {
            try {
                List<Object> resultsForConsumer = this.sendToConsumer(event, consumer);
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
