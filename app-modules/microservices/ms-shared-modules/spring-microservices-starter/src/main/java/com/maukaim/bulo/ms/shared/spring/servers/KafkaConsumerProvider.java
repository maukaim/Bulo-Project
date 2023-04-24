package com.maukaim.bulo.ms.shared.spring.servers;

import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaConsumer;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaConsumerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class KafkaConsumerProvider {
    protected KafkaConsumerFactory kafkaConsumerFactory;

    @Autowired
    public KafkaConsumerProvider(KafkaConsumerFactory kafkaConsumerFactory) {
        this.kafkaConsumerFactory = kafkaConsumerFactory;
    }

    public abstract List<KafkaConsumer<?>> getConsumers();
}
