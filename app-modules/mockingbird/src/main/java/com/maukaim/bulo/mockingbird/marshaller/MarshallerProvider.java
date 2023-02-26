package com.maukaim.bulo.mockingbird.marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.maukaim.bulo.serialization.shared.CommonMixinsConfig;
import com.maukaim.bulo.serialization.definitions.system.DefinitionSystemMixinsConfig;
import com.maukaim.bulo.serialization.flows.system.FlowSystemMixinsConfig;
import com.maukaim.bulo.serialization.data.types.DataTypeMixInsConfig;
import com.maukaim.bulo.serialization.stages.system.StageSystemMixinsConfig;
import com.maukaim.bulo.trigger.serialization.scheduler.client.SchedulerMixinsConfig;
import com.maukaim.bulo.serialization.triggers.system.TriggersMixinsConfig;

import java.util.HashMap;
import java.util.Map;

public class MarshallerProvider {

    public static MockingBirdMarshaller get() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<Class<?>, Class<?>> mixIns = getAllMixIns();
        mixIns.forEach(objectMapper::addMixIn);
        objectMapper.registerModule(new JavaTimeModule());

        return new MockingBirdMarshaller(objectMapper);
    }

    private static Map<Class<?>, Class<?>> getAllMixIns() {
        return new HashMap<>() {{
            putAll(DefinitionSystemMixinsConfig.DEFINITIONS_SYSTEM_JACKSON_MIXIN);
            putAll(DataTypeMixInsConfig.DATA_TYPES_COMMONS_MIXINS);
            putAll(StageSystemMixinsConfig.STAGES_SERVICE_JACKSON_MIXIN);
            putAll(FlowSystemMixinsConfig.FLOW_SERIALIZATION_JACKSON_MIXIN);
            putAll(SchedulerMixinsConfig.TRIGGER_SCHEDULER_JACKSON_MIXIN);
            putAll(TriggersMixinsConfig.TRIGGERS_JACKSON_MIXIN);
            putAll(CommonMixinsConfig.COMMON_SERIALIZATION_JACKSON_MIXINS);
        }};
    }
}
