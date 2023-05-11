package com.maukaim.bulo.ms.shared.system.communication.kafka;

import com.maukaim.bulo.app.shared.system.communication.core.SystemEventSender;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaSender implements SystemEventSender, AutoCloseable {
    private final KafkaProducer<Object, String> kafkaProducer;
    private final String topicName;

    public KafkaSender(KafkaProducer<Object, String> kafkaProducer, String topicName) {
        this.topicName = topicName;
        this.kafkaProducer = kafkaProducer;
    }

    public void send(String eventAsString) {
        ProducerRecord<Object, String> record = new ProducerRecord<>(topicName, eventAsString);
        this.kafkaProducer.send(record);
    }

    @Override
    public String getIdentifier() {
        return String.format("Kafka Consumer[%s]", getTopicName());
    }

    private String getTopicName() {
        return topicName;
    }

    @Override
    public void close() {
        this.kafkaProducer.close();
    }
}
