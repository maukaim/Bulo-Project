package com.maukaim.bulo.ms.shared.system.communication.kafka.topics;

public enum KafkaTopicName {
    DEFINITION_TOPIC,
    EXECUTOR_UPDATE_TOPIC,
    STAGE_RUN_READY_TO_START_TOPIC,
    STAGE_UPDATE_TOPIC,
    FLOW_UPDATE_TOPIC,
    FLOW_RUN_UPDATE_TOPIC,
    STAGE_RUN_UPDATE_TOPIC,
    STAGE_RUN_TO_BE_CANCELLED_TOPIC,

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
