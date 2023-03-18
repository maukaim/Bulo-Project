package com.maukaim.bulo.ms.shared.system.communication.kafka;

import com.maukaim.bulo.app.shared.system.communication.core.SystemEventSender;

public abstract class KafkaEventProducer<KEY,VALUE> implements SystemEventSender {
    private String topicName;

    @Override
    public String getIdentifier() {
        return "Kafka Consumer.";
    }
}
