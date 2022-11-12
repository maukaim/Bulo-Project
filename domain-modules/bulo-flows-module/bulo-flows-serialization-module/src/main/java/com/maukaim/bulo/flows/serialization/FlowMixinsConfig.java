package com.maukaim.bulo.flows.serialization;


import com.maukaim.bulo.flows.io.definition.ParameterDefinitionDto;
import com.maukaim.bulo.flows.io.definition.StageInputDefinitionDto;
import com.maukaim.bulo.flows.io.definition.StageOutputDefinitionDto;
import com.maukaim.bulo.flows.io.definition.stageDefinitionDto;
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

public class FlowMixinsConfig {


    public static Map<Class<?>, Class<?>> FLOWS_SERIALIZATION_JACKSON_MIXIN = Map.of(
            FlowEvent.class, FlowEventMixIn.class,
            CreateFlowInstruction.class, CreateFlowInstructionMixIn.class,
            RemoveFlowInstruction.class, RemoveFlowInstructionMixIn.class,
            FlowDto.class, FlowDtoMixIn.class,
            OwnerKeyDto.class, OwnerKeyDtoMixIn.class,
            FlowStageDto.class, FlowStageDtoMixIn.class,
            IoDependencyDto.class, IoDependencyDtoMixIn.class,
            InputProviderDto.class, InputProviderDtoMixIn.class,
            FailureAlternativeRoutesDto.class, FailureAlternativeRouteDtoMixIn.class
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

/**
 * Next:
 * Consume Flow in Orchestrator with failureAlternativeRoutes
 * Add this in OrchestrableContext, so FlowRun can use it.
 * in FlowRunServiceImpl, quand des stages sont failed,
 * avant de changer le status du FlowRun en FAILED, check si une alternative exist.
 * Si une existe, en faire une nextStage, donc l'ajouter, et egalement ignorer la failed stage dans le calcul
 * du status.
 * Penser a checker/changer comment on verifie qu'un flow est bien SUCCESS.
 * Penser a checker/changer que un FlowRun n'espere que 1 StageRun par ContextStage.
 */