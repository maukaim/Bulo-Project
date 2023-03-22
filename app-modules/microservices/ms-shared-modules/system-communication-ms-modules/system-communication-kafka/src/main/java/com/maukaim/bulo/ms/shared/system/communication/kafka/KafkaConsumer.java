package com.maukaim.bulo.ms.shared.system.communication.kafka;

import reactor.kafka.receiver.KafkaReceiver;

public abstract class KafkaConsumer<E> {
    //TODO: implement, consume a certain type, certain topics.
    protected abstract void transfer(E event);

    public KafkaConsumer(KafkaReceiver<String,Object> receiver){
        receiver.receive().subscribe(record -> {
            System.out.printf("Received message with key: %s and value: %s%n", record.key(), record.value());
            record.receiverOffset().acknowledge(); // Acknowledge the record after processing
        });
    }

    protected abstract void subscribe();

    /**TODO:
     * Pour avoir son Consumer ready, un Microservice ne devrait seulement fournir les info suivante:
     * - Le MicroserviceType (pour que le system connaisse le topic)
     * - Le runnable quand on recoit l'info (Pour rediriger)
     * - Le Class<E> de l'event recu (pour deserialization)
     *
     * Le reste est géré par le system.
     * - Le topic est déterminé par le MicroserviceType
     * - Le Consumer est créé par le system
     * - Le Consumer est lancé par le system
     * - Le Consumer est fermé par le system
     *
     */
}
