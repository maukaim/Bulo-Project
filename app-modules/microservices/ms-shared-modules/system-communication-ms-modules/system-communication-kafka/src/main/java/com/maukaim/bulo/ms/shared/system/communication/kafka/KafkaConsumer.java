package com.maukaim.bulo.ms.shared.system.communication.kafka;

import reactor.kafka.receiver.KafkaReceiver;

import java.util.function.Consumer;

public class KafkaConsumer<E> {
    private final KafkaReceiver<String,String> receiver;

    public KafkaConsumer(KafkaReceiver<String,String> receiver,
                         KafkaMessageDeserializer<E> deserializer,
                         Consumer<E> consumer){
        this.receiver = receiver;
        receiver.receive().subscribe(record -> {
            E event = deserializer.deserialize(record.value());
            System.out.printf("Received message From kafka with key: %s and value: %s%n", record.key(), record.value());
            consumer.accept(event);
            record.receiverOffset().acknowledge();
        });
    }
}
