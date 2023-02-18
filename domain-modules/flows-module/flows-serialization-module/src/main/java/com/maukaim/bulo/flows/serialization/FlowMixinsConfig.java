package com.maukaim.bulo.flows.serialization;


import com.maukaim.bulo.io.flows.system.definition.ParameterDefinitionDto;
import com.maukaim.bulo.io.flows.system.definition.StageInputDefinitionDto;
import com.maukaim.bulo.io.flows.system.definition.StageOutputDefinitionDto;
import com.maukaim.bulo.io.flows.system.definition.stageDefinitionDto;
import com.maukaim.bulo.io.flows.system.events.CreateFlowInstruction;
import com.maukaim.bulo.io.flows.system.events.FlowEvent;
import com.maukaim.bulo.io.flows.system.events.RemoveFlowInstruction;
import com.maukaim.bulo.io.flows.system.events.StageDefinitionEvent;
import com.maukaim.bulo.io.flows.system.events.StageUpdateEvent;
import com.maukaim.bulo.io.flows.system.flow.FlowDto;
import com.maukaim.bulo.io.flows.system.flow.FlowStageDto;
import com.maukaim.bulo.io.flows.system.flow.InputProviderDto;
import com.maukaim.bulo.io.flows.system.flow.IoDependencyDto;
import com.maukaim.bulo.io.flows.system.flow.OwnerKeyDto;
import com.maukaim.bulo.io.flows.system.stage.FunctionalStageDto;
import com.maukaim.bulo.io.flows.system.stage.ParameterDto;
import com.maukaim.bulo.io.flows.system.stage.StageDto;
import com.maukaim.bulo.io.flows.system.stage.TechnicalStageDto;
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
