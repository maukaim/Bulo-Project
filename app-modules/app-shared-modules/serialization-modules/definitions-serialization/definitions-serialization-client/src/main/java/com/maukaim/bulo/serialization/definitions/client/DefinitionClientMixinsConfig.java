package com.maukaim.bulo.serialization.definitions.client;


import com.maukaim.bulo.io.definitions.client.CreateStageDefinitionInstruction;
import com.maukaim.bulo.io.definitions.client.dtos.ParameterDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageInputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageOutputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.FsStageDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.FunctionalStageDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.InputProviderDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.IoDependencyDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.OutputProviderDto;
import com.maukaim.bulo.io.definitions.client.dtos.technical.TechnicalStageDefinitionDto;
import com.maukaim.bulo.serialization.definitions.client.mixins.ParameterDefinitionDtoMixIn;
import com.maukaim.bulo.serialization.definitions.client.mixins.StageDefinitionCreateInstructionMixIn;
import com.maukaim.bulo.serialization.definitions.client.mixins.StageDefinitionDtoMixIn;
import com.maukaim.bulo.serialization.definitions.client.mixins.StageInputDefinitionDtoMixIn;
import com.maukaim.bulo.serialization.definitions.client.mixins.StageOutputDefinitionDtoMixIn;
import com.maukaim.bulo.serialization.definitions.client.mixins.functional.FsStageDtoMixIn;
import com.maukaim.bulo.serialization.definitions.client.mixins.functional.FunctionalStageDefinitionDtoMixIn;
import com.maukaim.bulo.serialization.definitions.client.mixins.functional.InputProviderDtoMixIn;
import com.maukaim.bulo.serialization.definitions.client.mixins.functional.IoDependencyDtoMixIn;
import com.maukaim.bulo.serialization.definitions.client.mixins.functional.OutputProviderDtoMixIn;
import com.maukaim.bulo.serialization.definitions.client.mixins.technical.TechnicalStageDefinitionDtoMixIn;

import java.util.HashMap;
import java.util.Map;

public class DefinitionClientMixinsConfig {

    private static final Map<Class<?>, Class<?>> COMMON_DEFINITIONS_MIXINS = Map.of(
            CreateStageDefinitionInstruction.class, StageDefinitionCreateInstructionMixIn.class,

            StageDefinitionDto.class, StageDefinitionDtoMixIn.class,

            StageInputDefinitionDto.class, StageInputDefinitionDtoMixIn.class,
            StageOutputDefinitionDto.class, StageOutputDefinitionDtoMixIn.class,
            ParameterDefinitionDto.class, ParameterDefinitionDtoMixIn.class
    );
    private static final Map<Class<?>, Class<?>> TECHNICAL_DEFINITIONS_MIXINS = Map.of(
            TechnicalStageDefinitionDto.class, TechnicalStageDefinitionDtoMixIn.class
    );

    private static final Map<Class<?>, Class<?>> FUNCTIONAL_DEFINITIONS_MIXINS = Map.of(
            FunctionalStageDefinitionDto.class, FunctionalStageDefinitionDtoMixIn.class,

            FsStageDto.class, FsStageDtoMixIn.class,
            InputProviderDto.class, InputProviderDtoMixIn.class,
            IoDependencyDto.class, IoDependencyDtoMixIn.class,
            OutputProviderDto.class, OutputProviderDtoMixIn.class
    );

    public static final Map<Class<?>, Class<?>> DEFINITIONS_CLIENT_JACKSON_MIXIN = new HashMap<>() {{
        putAll(COMMON_DEFINITIONS_MIXINS);
        putAll(FUNCTIONAL_DEFINITIONS_MIXINS);
        putAll(TECHNICAL_DEFINITIONS_MIXINS);
    }};

}
