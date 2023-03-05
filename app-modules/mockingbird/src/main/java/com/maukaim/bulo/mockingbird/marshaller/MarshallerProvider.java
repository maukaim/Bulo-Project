package com.maukaim.bulo.mockingbird.marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.maukaim.bulo.serialization.definitions.client.DefinitionClientMixinsConfig;
import com.maukaim.bulo.serialization.flows.client.FlowClientMixinsConfig;
import com.maukaim.bulo.serialization.shared.CommonMixinsConfig;
import com.maukaim.bulo.serialization.data.types.DataTypeMixInsConfig;
import com.maukaim.bulo.serialization.stages.client.StageClientMixinsConfig;
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
            putAll(DefinitionClientMixinsConfig.DEFINITIONS_CLIENT_JACKSON_MIXIN);
            putAll(DataTypeMixInsConfig.DATA_TYPES_COMMONS_MIXINS);
            putAll(StageClientMixinsConfig.STAGES_SERVICE_JACKSON_MIXIN);
            putAll(FlowClientMixinsConfig.FLOW_CLIENT_SERIALIZATION_JACKSON_MIXIN);
            putAll(SchedulerMixinsConfig.TRIGGER_SCHEDULER_JACKSON_MIXIN);
            putAll(TriggersMixinsConfig.TRIGGERS_JACKSON_MIXIN);
            putAll(CommonMixinsConfig.COMMON_SERIALIZATION_JACKSON_MIXINS);
        }};
    }
}
