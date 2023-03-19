package com.maukaim.bulo.ms.shared.spring.servers;

import com.maukaim.bulo.app.shared.system.communication.core.SystemEventConsumerResolver;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaSender;
import com.maukaim.bulo.ms.shared.system.communication.kafka.topics.KafkaTopicName;
import com.maukaim.bulo.shared.server.core.model.KafkaProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaSenderResolver implements SystemEventConsumerResolver<KafkaSender, MicroServiceEventType> {
    private final Map<MicroServiceEventType, KafkaTopicName> mapOfTopics;
    private final Map<MicroServiceEventType, KafkaSender> producersMap;
    private final KafkaProperties kafkaProperties;

    public KafkaSenderResolver(KafkaProperties kafkaProperties,
                               Map<MicroServiceEventType, KafkaTopicName> mapOfTopics,
                               Map<MicroServiceEventType, KafkaSender> defaultProducersMap) {
        this.kafkaProperties = kafkaProperties;
        this.mapOfTopics = mapOfTopics;
        this.producersMap = new HashMap<>(defaultProducersMap);
    }

    @Override
    public List<KafkaSender> resolve(MicroServiceEventType eventType) {
        if (producersMap.containsKey(eventType)) {
            return List.of(producersMap.get(eventType));
        }

        if (mapOfTopics.containsKey(eventType)) {
            KafkaTopicName topicName = mapOfTopics.get(eventType);
            KafkaSender kafkaSender = createKafkaSender(topicName);
            producersMap.put(eventType, kafkaSender);
            return List.of(kafkaSender);
        }
        return List.of();
    }

    private KafkaSender createKafkaSender(KafkaTopicName topicName) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(",", kafkaProperties.getBrokers()));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new KafkaSender(new KafkaProducer<>(props), topicName.getTopicName());
    }

}
