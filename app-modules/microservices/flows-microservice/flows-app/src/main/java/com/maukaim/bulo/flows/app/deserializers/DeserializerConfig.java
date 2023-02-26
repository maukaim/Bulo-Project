package com.maukaim.bulo.flows.app.deserializers;


import com.maukaim.bulo.commons.serialization.CommonMixinsConfig;
import com.maukaim.bulo.definitions.registry.serialization.DefinitionMixinsConfig;
import com.maukaim.bulo.flows.serialization.FlowMixinsConfig;
import com.maukaim.bulo.serialization.data.types.DataTypeMixInsConfig;
import com.maukaim.bulo.serialization.stage.StageMixinsConfig;
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
            jacksonObjectMapperBuilder.mixIns(FlowMixinsConfig.SERIALIZATION_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(StageMixinsConfig.STAGES_SERVICE_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(DataTypeMixInsConfig.DATA_TYPES_COMMONS_MIXINS);
            jacksonObjectMapperBuilder.mixIns(DefinitionMixinsConfig.DEFINITIONS_JACKSON_MIXIN);
        };
    }
}
