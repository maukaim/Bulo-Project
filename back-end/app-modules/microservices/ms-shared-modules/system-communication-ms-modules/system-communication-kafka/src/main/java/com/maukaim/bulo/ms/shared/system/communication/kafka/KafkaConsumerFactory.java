package com.maukaim.bulo.ms.shared.system.communication.kafka;

import com.maukaim.bulo.marshalling.Marshaller;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.ms.shared.system.communication.kafka.topics.KafkaTopicName;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.common.serialization.StringDeserializer;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.function.Consumer;

public class KafkaConsumerFactory {
    private final Marshaller marshaller;
    private final List<String> bootstrapServers;
    private final Map<MicroServiceEventType, KafkaTopicName> mapOfTopics;

    public KafkaConsumerFactory(Marshaller marshaller,
                                List<String> bootstrapServers,
                                Map<MicroServiceEventType, KafkaTopicName> mapOfTopics) {
        this.marshaller = marshaller;
        this.bootstrapServers = bootstrapServers;
        this.mapOfTopics = mapOfTopics;
    }

    public <E> KafkaConsumer<E> create(MicroServiceEventType msEventType, Class<E> clazz, Consumer<E> consumer, OffsetResetStrategy strategy) {
        return create(msEventType, clazz, consumer, strategy, generateRandomGroupId());
    }

    public <E> KafkaConsumer<E> create(MicroServiceEventType msEventType, Class<E> clazz, Consumer<E> consumer, OffsetResetStrategy strategy, String groupId) {
        KafkaTopicName topicName = mapOfTopics.get(msEventType);
        if (topicName == null) {
            throw new IllegalArgumentException("No topic found for MicroServiceEventType: " + msEventType);
        }
        Properties props = getDefaultProperties(strategy);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return createConsumer(props, topicName.getTopicName(), clazz, consumer);
    }

    private <E> KafkaConsumer<E> createConsumer(Properties props, String topicName, Class<E> clazz, Consumer<E> consumer) {
        ReceiverOptions<String, String> options = ReceiverOptions.<String, String>create(props)
                .subscription(Collections.singleton(topicName));

        KafkaReceiver<String, String> receiver = KafkaReceiver.create(options);
        return new KafkaConsumer<>(receiver,
                (e) -> this.marshaller.unmarshall(e, clazz),
                consumer);
    }

    private Properties getDefaultProperties(OffsetResetStrategy strategy) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, ofStrategy(strategy));

        return props;
    }

    private String ofStrategy(OffsetResetStrategy strategy) {
        return strategy.toString().toLowerCase();
    }

    private String generateRandomGroupId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
