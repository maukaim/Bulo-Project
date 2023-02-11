package com.maukaim.bulo.definitions.registry.serialization;

import com.maukaim.bulo.commons.io.instructions.CreateStageDefinitionInstruction;
import com.maukaim.bulo.commons.io.instructions.models.ParameterDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.StageOutputDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.functional.*;
import com.maukaim.bulo.commons.io.instructions.models.technical.TechnicalStageDefinitionDto;
import com.maukaim.bulo.definitions.io.events.ExecutorUpdateEvent;
import com.maukaim.bulo.definitions.io.events.StageDefinitionEvent;
import com.maukaim.bulo.definitions.io.events.StageUpdateEvent;
import com.maukaim.bulo.definitions.io.stage.FunctionalStageDto;
import com.maukaim.bulo.definitions.io.stage.ParameterDto;
import com.maukaim.bulo.definitions.io.stage.StageDto;
import com.maukaim.bulo.definitions.io.stage.TechnicalStageDto;
import com.maukaim.bulo.definitions.registry.serialization.mixins.definition.*;
import com.maukaim.bulo.definitions.registry.serialization.mixins.definition.functional.*;
import com.maukaim.bulo.definitions.registry.serialization.mixins.definition.technical.TechnicalStageDefinitionDtoMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.stage.*;

import java.util.HashMap;
import java.util.Map;

public class DefinitionMixinsConfig {

    private static Map<Class<?>,Class<?>> COMMON_DEFINITIONS_MIXINS = Map.of(
            CreateStageDefinitionInstruction.class, StageDefinitionCreateInstructionMixIn.class,
            StageDefinitionEvent.class, StageDefinitionEventMixIn.class,
            ExecutorUpdateEvent.class, ExecutorUpdateEventMixIn.class,

            StageDefinitionDto.class, StageDefinitionDtoMixIn.class,

            StageInputDefinitionDto.class, StageInputDefinitionDtoMixIn.class,
            StageOutputDefinitionDto.class, StageOutputDefinitionDtoMixIn.class,
            ParameterDefinitionDto.class, ParameterDefinitionDtoMixIn.class
    );
    private static Map<Class<?>,Class<?>> TECHNICAL_DEFINITIONS_MIXINS = Map.of(
            TechnicalStageDefinitionDto.class, TechnicalStageDefinitionDtoMixIn.class
    );

    private static Map<Class<?>, Class<?>> FUNCTIONAL_DEFINITIONS_MIXINS = Map.of(
            FunctionalStageDefinitionDto.class, FunctionalStageDefinitionDtoMixIn.class,

            FsStageDto.class, FsStageDtoMixIn.class,
            InputProviderDto.class, InputProviderDtoMixIn.class,
            IoDependencyDto.class, IoDependencyDtoMixIn.class,
            OutputProviderDto.class, OutputProviderDtoMixIn.class
    );

    private static Map<Class<?>, Class<?>> STAGES_MIXINS = Map.of(
            StageUpdateEvent.class, StageUpdateEventMixIn.class,
            StageDto.class, StageDtoMixIn.class,
            ParameterDto.class, ParameterDtoMixIn.class,
            FunctionalStageDto.class, FunctionalStageDtoMixIn.class,
            TechnicalStageDto.class, TechnicalStageDtoMixIn.class
    );

    public static Map<Class<?>, Class<?>> DEFINITIONS_REGISTRY_JACKSON_MIXIN = new HashMap<>() {{
        putAll(COMMON_DEFINITIONS_MIXINS);
        putAll(FUNCTIONAL_DEFINITIONS_MIXINS);
        putAll(TECHNICAL_DEFINITIONS_MIXINS);
        putAll(STAGES_MIXINS);
    }};

}
