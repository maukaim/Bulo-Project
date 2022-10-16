package com.maukaim.bulo.definitions.registry.serialization;

import com.maukaim.bulo.commons.io.instructions.TechnicalStageDefinitionCreateInstruction;
import com.maukaim.bulo.definitions.io.events.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.commons.io.instructions.models.ParameterDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.StageOutputDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.TechnicalStageDefinitionDto;
import com.maukaim.bulo.definitions.registry.serialization.mixins.*;

import java.util.Map;

public class MixinsConfig {

    public static Map<Class<?>, Class<?>> DEFINITIONS_REGISTRY_JACKSON_MIXIN = Map.of(
            TechnicalStageDefinitionCreateInstruction.class, TechnicalStageDefinitionDeclarationEventMixIn.class,
            TechnicalStageDefinitionDto.class, TechnicalStageDefinitionDtoMixIn.class,
            StageInputDefinitionDto.class, StageInputDefinitionDtoMixIn.class,
            StageOutputDefinitionDto.class, StageOutputDefinitionDtoMixIn.class,
            ParameterDefinitionDto.class, ParameterDefinitionDtoMixIn.class,
            TechnicalStageDefinitionEvent.class, TechnicalStageDefinitionEventMixIn.class
    );
}
