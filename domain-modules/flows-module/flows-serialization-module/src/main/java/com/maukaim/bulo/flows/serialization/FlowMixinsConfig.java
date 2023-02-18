package com.maukaim.bulo.flows.serialization;


import com.maukaim.bulo.io.flows.definition.ParameterDefinitionDto;
import com.maukaim.bulo.io.flows.definition.StageInputDefinitionDto;
import com.maukaim.bulo.io.flows.definition.StageOutputDefinitionDto;
import com.maukaim.bulo.io.flows.definition.stageDefinitionDto;
import com.maukaim.bulo.io.flows.events.CreateFlowInstruction;
import com.maukaim.bulo.io.flows.events.FlowEvent;
import com.maukaim.bulo.io.flows.events.RemoveFlowInstruction;
import com.maukaim.bulo.io.flows.events.StageDefinitionEvent;
import com.maukaim.bulo.io.flows.events.StageUpdateEvent;
import com.maukaim.bulo.io.flows.flow.FlowDto;
import com.maukaim.bulo.io.flows.flow.FlowStageDto;
import com.maukaim.bulo.io.flows.flow.InputProviderDto;
import com.maukaim.bulo.io.flows.flow.IoDependencyDto;
import com.maukaim.bulo.io.flows.flow.OwnerKeyDto;
import com.maukaim.bulo.io.flows.stage.FunctionalStageDto;
import com.maukaim.bulo.io.flows.stage.ParameterDto;
import com.maukaim.bulo.io.flows.stage.StageDto;
import com.maukaim.bulo.io.flows.stage.TechnicalStageDto;
import com.maukaim.bulo.flows.serialization.mixins.definition.*;
import com.maukaim.bulo.flows.serialization.mixins.flow.*;
import com.maukaim.bulo.flows.serialization.mixins.stage.*;

import java.util.HashMap;
import java.util.Map;

public class FlowMixinsConfig {


    public static Map<Class<?>, Class<?>> FLOWS_SERIALIZATION_JACKSON_MIXIN = Map.of(
            FlowEvent.class, FlowEventMixIn.class,
            CreateFlowInstruction.class, CreateFlowInstructionMixIn.class,
            RemoveFlowInstruction.class, RemoveFlowInstructionMixIn.class,
            FlowDto.class, FlowDtoMixIn.class,
            OwnerKeyDto.class, OwnerKeyDtoMixIn.class,
            FlowStageDto.class, FlowStageDtoMixIn.class,
            IoDependencyDto.class, IoDependencyDtoMixIn.class,
            InputProviderDto.class, InputProviderDtoMixIn.class
    );

    public static Map<Class<?>, Class<?>> DEFINITIONS_SERIALIZATION_JACKSON_MIXIN = Map.of(
            StageDefinitionEvent.class, StageDefinitionUpdateEventMixIn.class,
            stageDefinitionDto.class, StageDefinitionDtoMixIn.class,
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
