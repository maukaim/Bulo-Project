package com.maukaim.bulo.ms.shared.system.communication.kafka.topics;

import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;

import java.util.Map;

public class KafkaTopicMapProvider {
    public static Map<MicroServiceEventType, KafkaTopicName> getTopicMap() {
        return Map.ofEntries(
                Map.entry(MicroServiceEventType.DEF_UPDATE, KafkaTopicName.DEFINITION_TOPIC),
                Map.entry(MicroServiceEventType.EXECUTOR_UPDATE, KafkaTopicName.EXECUTOR_UPDATE_TOPIC),
                Map.entry(MicroServiceEventType.STAGE_UPDATE, KafkaTopicName.STAGE_UPDATE_TOPIC),
                Map.entry(MicroServiceEventType.FLOW_UPDATE, KafkaTopicName.FLOW_UPDATE_TOPIC),
                Map.entry(MicroServiceEventType.FLOW_RUN_UPDATE, KafkaTopicName.FLOW_RUN_UPDATE_TOPIC),
                Map.entry(MicroServiceEventType.STAGE_RUN_UPDATE, KafkaTopicName.STAGE_RUN_UPDATE_TOPIC),
                Map.entry(MicroServiceEventType.STAGE_RUN_READY_TO_START_EVENT, KafkaTopicName.STAGE_RUN_READY_TO_START_TOPIC),
                Map.entry(MicroServiceEventType.NEED_STAGE_RUN_CANCEL, KafkaTopicName.STAGE_RUN_TO_BE_CANCELLED_TOPIC)

//                // I don't know what to choose?
//                MicroServiceEventType.STAGE_RUN_RESULT, should be just Database save?

//                // Should be REST calls only?
//                MicroServiceEventType.TRIGGER_FLOW_RUN
//                MicroServiceEventType.DEF_CREATE_INSTRUCTION,
        );
    }
}
