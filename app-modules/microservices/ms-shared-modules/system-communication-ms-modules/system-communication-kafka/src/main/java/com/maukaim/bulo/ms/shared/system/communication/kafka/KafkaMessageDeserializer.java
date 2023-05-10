package com.maukaim.bulo.ms.shared.system.communication.kafka;

@FunctionalInterface
public interface KafkaMessageDeserializer<E> {
    E deserialize(String message);
}
