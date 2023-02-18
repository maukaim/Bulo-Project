package com.maukaim.bulo.definitions.registry.serialization;

import com.maukaim.bulo.definitions.registry.serialization.mixins.ExecutorUpdateEventMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.ParameterDefinitionDtoMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.StageDefinitionCreateInstructionMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.StageDefinitionDtoMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.StageDefinitionEventMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.StageInputDefinitionDtoMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.StageOutputDefinitionDtoMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.functional.FsStageDtoMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.functional.FunctionalStageDefinitionDtoMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.functional.InputProviderDtoMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.functional.IoDependencyDtoMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.functional.OutputProviderDtoMixIn;
import com.maukaim.bulo.definitions.registry.serialization.mixins.technical.TechnicalStageDefinitionDtoMixIn;
import com.maukaim.bulo.io.definitions.client.CreateStageDefinitionInstruction;
import com.maukaim.bulo.io.definitions.client.models.ParameterDefinitionDto;
import com.maukaim.bulo.io.definitions.client.models.StageDefinitionDto;
import com.maukaim.bulo.io.definitions.client.models.StageInputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.models.StageOutputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.models.functional.FsStageDto;
import com.maukaim.bulo.io.definitions.client.models.functional.FunctionalStageDefinitionDto;
import com.maukaim.bulo.io.definitions.client.models.functional.InputProviderDto;
import com.maukaim.bulo.io.definitions.client.models.functional.IoDependencyDto;
import com.maukaim.bulo.io.definitions.client.models.functional.OutputProviderDto;
import com.maukaim.bulo.io.definitions.client.models.technical.TechnicalStageDefinitionDto;
import com.maukaim.bulo.io.definitions.system.events.ExecutorUpdateEvent;
import com.maukaim.bulo.io.definitions.system.events.StageDefinitionEvent;

import java.util.HashMap;
import java.util.Map;

public class DefinitionMixinsConfig {

    private static Map<Class<?>, Class<?>> COMMON_DEFINITIONS_MIXINS = Map.of(
            CreateStageDefinitionInstruction.class, StageDefinitionCreateInstructionMixIn.class,
            StageDefinitionEvent.class, StageDefinitionEventMixIn.class,
            ExecutorUpdateEvent.class, ExecutorUpdateEventMixIn.class,

            StageDefinitionDto.class, StageDefinitionDtoMixIn.class,

            StageInputDefinitionDto.class, StageInputDefinitionDtoMixIn.class,
            StageOutputDefinitionDto.class, StageOutputDefinitionDtoMixIn.class,
            ParameterDefinitionDto.class, ParameterDefinitionDtoMixIn.class
    );
    private static Map<Class<?>, Class<?>> TECHNICAL_DEFINITIONS_MIXINS = Map.of(
            TechnicalStageDefinitionDto.class, TechnicalStageDefinitionDtoMixIn.class
    );

    private static Map<Class<?>, Class<?>> FUNCTIONAL_DEFINITIONS_MIXINS = Map.of(
            FunctionalStageDefinitionDto.class, FunctionalStageDefinitionDtoMixIn.class,

            FsStageDto.class, FsStageDtoMixIn.class,
            InputProviderDto.class, InputProviderDtoMixIn.class,
            IoDependencyDto.class, IoDependencyDtoMixIn.class,
            OutputProviderDto.class, OutputProviderDtoMixIn.class
    );

    public static Map<Class<?>, Class<?>> DEFINITIONS_REGISTRY_JACKSON_MIXIN = new HashMap<>() {{
        putAll(COMMON_DEFINITIONS_MIXINS);
        putAll(FUNCTIONAL_DEFINITIONS_MIXINS);
        putAll(TECHNICAL_DEFINITIONS_MIXINS);
    }};

}
