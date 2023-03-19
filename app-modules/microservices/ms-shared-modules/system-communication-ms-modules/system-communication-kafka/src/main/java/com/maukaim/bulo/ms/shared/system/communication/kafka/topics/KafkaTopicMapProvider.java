package com.maukaim.bulo.ms.shared.system.communication.kafka.topics;

import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;

import java.util.HashMap;
import java.util.Map;

public class KafkaTopicMapProvider {
    public static Map<MicroServiceEventType, KafkaTopicName> getTopicMap() {
        Map<MicroServiceEventType, KafkaTopicName> one = Map.of(
                MicroServiceEventType.DEF_UPDATE, KafkaTopicName.TEST_TOPIC
//                MicroServiceEventType.EXECUTOR_UPDATE, KafkaTopicName.TEST_TOPIC
//                MicroServiceEventType.DEF_CREATE_INSTRUCTION,
//                MicroServiceEventType.NEED_STAGE_RUN_EVENT,
//                MicroServiceEventType.FLOW_RUN_UPDATE,
//                MicroServiceEventType.STAGE_UPDATE,
//                MicroServiceEventType.FLOW_UPDATE,
//                MicroServiceEventType.DEF_UPDATE,
//                MicroServiceEventType.EXECUTOR_UPDATE,
        );
//        Map<MicroServiceEventType, Class<?>> second = Map.of(
//                MicroServiceEventType.STAGE_RUN_RESULT,
//                MicroServiceEventType.STAGE_RUN_UPDATE,
//                MicroServiceEventType.NEED_STAGE_RUN_CANCEL,
//        );

        return new HashMap<>() {{
            putAll(one);
//            putAll(second);
        }};
    }
}
