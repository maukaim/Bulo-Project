package com.maukaim.bulo.runs.orchestrators.app.deserializers;

import com.maukaim.bulo.serialization.shared.CommonMixinsConfig;
import com.maukaim.bulo.serialization.executors.system.ExecutorMixinsConfig;
import com.maukaim.bulo.serialization.flows.system.FlowSystemMixinsConfig;
import com.maukaim.bulo.serialization.runs.orchestrators.system.OrchestratorMixinsConfig;
import com.maukaim.bulo.serialization.data.types.DataTypeMixInsConfig;
import com.maukaim.bulo.serialization.definitions.system.DefinitionSystemMixinsConfig;
import com.maukaim.bulo.serialization.stages.system.StageSystemMixinsConfig;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.maukaim.bulo.serialization.data.types.DataTypeMixInsConfig.COMMON_DESERIALIZERS;

@Configuration
public class DeserializerConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.deserializersByType(COMMON_DESERIALIZERS);
            jacksonObjectMapperBuilder.mixIns(CommonMixinsConfig.COMMON_SERIALIZATION_JACKSON_MIXINS);

            jacksonObjectMapperBuilder.mixIns(OrchestratorMixinsConfig.ORCHESTRATOR_SERIALIZATION_JACKSON_MIXIN);

            jacksonObjectMapperBuilder.mixIns(ExecutorMixinsConfig.EXECUTORS_SERVICE_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(FlowSystemMixinsConfig.FLOW_SERIALIZATION_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(DefinitionSystemMixinsConfig.DEFINITIONS_SYSTEM_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(DataTypeMixInsConfig.DATA_TYPES_COMMONS_MIXINS);
            jacksonObjectMapperBuilder.mixIns(StageSystemMixinsConfig.STAGES_SERVICE_JACKSON_MIXIN);
        };
    }
}
