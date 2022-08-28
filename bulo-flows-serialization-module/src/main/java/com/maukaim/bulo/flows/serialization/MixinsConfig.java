package com.maukaim.bulo.flows.serialization;


import com.maukaim.bulo.flows.io.definition.ParameterDefinitionDto;
import com.maukaim.bulo.flows.io.definition.StageInputDefinitionDto;
import com.maukaim.bulo.flows.io.definition.StageOutputDefinitionDto;
import com.maukaim.bulo.flows.io.definition.TechnicalStageDefinitionDto;
import com.maukaim.bulo.flows.io.events.*;
import com.maukaim.bulo.flows.io.flow.*;
import com.maukaim.bulo.flows.io.stage.FunctionalStageDto;
import com.maukaim.bulo.flows.io.stage.ParameterDto;
import com.maukaim.bulo.flows.io.stage.StageDto;
import com.maukaim.bulo.flows.io.stage.TechnicalStageDto;
import com.maukaim.bulo.flows.serialization.mixins.definition.*;
import com.maukaim.bulo.flows.serialization.mixins.flow.*;
import com.maukaim.bulo.flows.serialization.mixins.stage.*;

import java.util.HashMap;
import java.util.Map;

public class MixinsConfig {


    public static Map<Class<?>, Class<?>> FLOWS_SERIALIZATION_JACKSON_MIXIN = Map.of(
            FlowEvent.class, FlowEventMixIn.class,
            PutFlowInstruction.class, CreateFlowInstructionMixIn.class,
            RemoveFlowInstruction.class, RemoveFlowInstructionMixIn.class,
            FlowDto.class, FlowDtoMixIn.class,
            OwnerKeyDto.class, OwnerKeyDtoMixIn.class,
            FlowStageDto.class, FlowStageDtoMixIn.class,
            IoDependencyDto.class, IoDependencyDtoMixIn.class,
            InputProviderDto.class, InputProviderDtoMixIn.class
    );

    public static Map<Class<?>, Class<?>> DEFINITIONS_SERIALIZATION_JACKSON_MIXIN = Map.of(
            TechnicalStageDefinitionEvent.class, TechnicalStageDefinitionUpdateEventMixIn.class,
            TechnicalStageDefinitionDto.class, TechnicalStageDefinitionDtoMixIn.class,
            StageInputDefinitionDto.class, StageInputDefinitionDtoMixIn.class,
            StageOutputDefinitionDto.class, StageOutputDefinitionDtoMixIn.class,
            ParameterDefinitionDto.class, ParameterDefinitionDtoMixIn.class
    );

    public static Map<Class<?>, Class<?>> STAGES_SERIALIZATION_JACKSON_MIXIN = Map.of(
            StageUpdateEvent.class, StageUpdateEventMixIn.class,
            StageDto.class, StageDtoMixIn.class,
            TechnicalStageDto.class, TechnicalStageDtoMixIn.class,
            FunctionalStageDto.class, FunctionalStageDtoMixIn.class,
            ParameterDto.class, ParameterDtoMixIn.class
    );
    public static Map<Class<?>, Class<?>> SERIALIZATION_JACKSON_MIXIN = new HashMap<>() {{
        putAll(FLOWS_SERIALIZATION_JACKSON_MIXIN);
        putAll(DEFINITIONS_SERIALIZATION_JACKSON_MIXIN);
        putAll(STAGES_SERIALIZATION_JACKSON_MIXIN);
    }};

}
