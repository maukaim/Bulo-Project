package com.maukaim.bulo.ms.shared.system.communication.kafka;

import com.maukaim.bulo.app.shared.system.communication.core.StdSystemConnector;
import com.maukaim.bulo.app.shared.system.communication.core.SystemEventConsumerResolver;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;

import java.rmi.ConnectIOException;
import java.util.List;

public class KafkaSystemEventConnector extends StdSystemConnector<KafkaEventProducer<?,?>, MicroServiceEventType> {

    public KafkaSystemEventConnector(SystemEventConsumerResolver<KafkaEventProducer<?,?>,MicroServiceEventType> systemEventSenderResolver, boolean isStrictMode) {
        super(systemEventSenderResolver, isStrictMode);
    }

    @Override
    protected List<Object> send(Object event, KafkaEventProducer<?,?> kafkaProducer) throws ConnectIOException {
        //TODO: Marshall the event
        // Send it to Topic. If can't connect, exception
        return List.of();
    }
}
