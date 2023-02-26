package com.maukaim.bulo.mockingbird.marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.maukaim.bulo.commons.serialization.CommonMixinsConfig;
import com.maukaim.bulo.serialization.definitions.client.DefinitionClientMixinsConfig;
import com.maukaim.bulo.serialization.definitions.system.DefinitionSystemMixinsConfig;
import com.maukaim.bulo.flows.serialization.FlowMixinsConfig;
import com.maukaim.bulo.serialization.data.types.DataTypeMixInsConfig;
import com.maukaim.bulo.serialization.stage.StageMixinsConfig;
import com.maukaim.bulo.trigger.scheduler.serialization.SchedulerMixinsConfig;
import com.maukaim.bulo.triggers.serialization.TriggersMixinsConfig;

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
            putAll(StageMixinsConfig.STAGES_SERVICE_JACKSON_MIXIN);
            putAll(FlowMixinsConfig.SERIALIZATION_JACKSON_MIXIN);
            putAll(SchedulerMixinsConfig.TRIGGER_SCHEDULER_JACKSON_MIXIN);
            putAll(TriggersMixinsConfig.TRIGGERS_JACKSON_MIXIN);
            putAll(CommonMixinsConfig.COMMON_SERIALIZATION_JACKSON_MIXINS);
        }};
    }
}
