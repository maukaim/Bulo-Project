package com.maukaim.bulo.definitions.registry.serialization;

import com.maukaim.bulo.definitions.io.events.TechnicalStageDefinitionDeclarationEvent;
import com.maukaim.bulo.definitions.io.events.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.definitions.io.models.ParameterDefinitionDto;
import com.maukaim.bulo.definitions.io.models.StageInputDefinitionDto;
import com.maukaim.bulo.definitions.io.models.StageOutputDefinitionDto;
import com.maukaim.bulo.definitions.io.models.TechnicalStageDefinitionDto;
import com.maukaim.bulo.definitions.registry.serialization.mixins.*;

import java.util.Map;

public class MixinsConfig {

    public static Map<Class<?>, Class<?>> DEFINITIONS_REGISTRY_JACKSON_MIXIN = Map.of(
            TechnicalStageDefinitionDeclarationEvent.class, TechnicalStageDefinitionDeclarationEventMixIn.class,
            TechnicalStageDefinitionDto.class, TechnicalStageDefinitionMixIn.class,
            StageInputDefinitionDto.class, StageInputDefinitionDtoMixIn.class,
            StageOutputDefinitionDto.class, StageOutputDefinitionDtoMixIn.class,
            ParameterDefinitionDto.class, ParameterDefinitionDtoMixIn.class,
            TechnicalStageDefinitionEvent.class, TechnicalStageDefinitionEventMixIn.class
    );
}
