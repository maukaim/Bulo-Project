package com.maukaim.bulo.ms.shared.system.communication.kafka.topics;

public enum KafkaTopicName {
    DEFINITION_TOPIC,
    TEST_TOPIC("quickstart-events");

    private final String topicName;

    KafkaTopicName(String topicName) {
        this.topicName = topicName;
    }
    KafkaTopicName() {
        this.topicName = topicFromName();
    }

    private String topicFromName() {
        return this.name().replaceAll("_", "-").toLowerCase();
    }

    public String getTopicName() {
        return topicName;
    }
}
