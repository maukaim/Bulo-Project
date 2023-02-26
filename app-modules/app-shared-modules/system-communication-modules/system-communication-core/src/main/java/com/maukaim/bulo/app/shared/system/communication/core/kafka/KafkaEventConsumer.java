package com.maukaim.bulo.app.shared.system.communication.core.kafka;

import com.maukaim.bulo.app.shared.system.communication.core.SystemEventConsumer;

public class KafkaEventConsumer implements SystemEventConsumer {
    private String topicName;

    @Override
    public String getIdentifier() {
        return "Kafka Consumer.";
    }
}
