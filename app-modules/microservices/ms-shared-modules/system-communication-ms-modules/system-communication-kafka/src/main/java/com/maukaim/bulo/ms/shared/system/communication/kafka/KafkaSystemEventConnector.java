package com.maukaim.bulo.ms.shared.system.communication.kafka;

import com.maukaim.bulo.app.shared.system.communication.core.StdSystemConnector;
import com.maukaim.bulo.app.shared.system.communication.core.SystemEventConsumerResolver;
import com.maukaim.bulo.marshalling.Marshaller;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;

import java.rmi.ConnectIOException;
import java.util.List;

public class KafkaSystemEventConnector extends StdSystemConnector<KafkaSender, MicroServiceEventType> {
    private final Marshaller marshaller;


    public KafkaSystemEventConnector(SystemEventConsumerResolver<KafkaSender, MicroServiceEventType> systemEventSenderResolver,
                                     Marshaller marshaller, boolean isStrictMode) {
        super(systemEventSenderResolver, isStrictMode);
        this.marshaller = marshaller;
    }

    @Override
    protected List<Object> send(Object event, KafkaSender kafkaProducer) throws ConnectIOException {
        String eventAsJson = marshaller.marshall(event);
        kafkaProducer.send(eventAsJson);
        return List.of();
    }
}
