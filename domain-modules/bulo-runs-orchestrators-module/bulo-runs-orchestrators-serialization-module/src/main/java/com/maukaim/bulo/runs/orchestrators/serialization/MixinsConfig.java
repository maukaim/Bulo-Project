package com.maukaim.bulo.runs.orchestrators.serialization;

import com.maukaim.bulo.commons.io.instructions.models.functional.OutputProviderDto;
import com.maukaim.bulo.runs.orchestrators.io.events.*;
import com.maukaim.bulo.runs.orchestrators.io.models.FunctionalStageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.TechnicalStageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.definition.FsStageDto;
import com.maukaim.bulo.runs.orchestrators.io.models.definition.IoDependencyDto;
import com.maukaim.bulo.runs.orchestrators.io.models.definition.StageDefinitionDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.*;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.*;
import com.maukaim.bulo.runs.orchestrators.io.models.stage.StageDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.events.*;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.*;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.definition.FsStageDtoMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.definition.IoDependencyDtoMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.definition.OutputProviderDtoMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.definition.StageDefinitionDtoMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.stage.StageDtoMixIn;

import java.util.HashMap;
import java.util.Map;

public class MixinsConfig {

    public static Map<Class<?>, Class<?>> FLOW_RUN_MIXINS = Map.of(
            FlowRunEvent.class, FlowRunEventMixIn.class,
            FlowRunDto.class, FlowRunDtoMixIn.class,
            ExecutionGraphDto.class, ExecutionGraphDtoMixIn.class,
            FlowRunStageDto.class, FlowRunStageDtoMixIn.class,
            StageRunAncestorDto.class, FlowStageAncestorDtoMixIn.class,
            FlowStageDependencyDto.class, FlowStageDependencyDtoMixIn.class,
            TechnicalStageRunDto.class, TechnicalStageRunDtoMixIn.class,
            FunctionalStageRunDto.class, FunctionalStageRunDtoMixIn.class,
            StageRunDependencyDto.class, StageRunDependencyDtoMixIn.class,
            com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunAncestorDto.class, StageRunAncestorDtoMixIn.class
    );

    public static Map<Class<?>, Class<?>> STAGE_RUN_EVENTS_MIXINS = Map.of(
            AcknowledgeRequestStageRunEvent.class, AcknowledgeRequestStageRunEventMixIn.class,
            RunCancelledStageRunEvent.class, StandardStageRunEventMixIn.class,
            RunFailedStageRunEvent.class, StandardStageRunEventMixIn.class,
            RunSuccessfulStageRunEvent.class, StandardStageRunEventMixIn.class,
            StartRunStageRunEvent.class, StandardStageRunEventMixIn.class,
            BasicStageRunEvent.class, BasicStageRunEventMixIn.class
    );

    public static Map<Class<?>,Class<?>> FLOW_MIXINS = Map.of(
            FlowEvent.class, FlowEventMixIn.class,
            FlowDto.class, FlowDtoMixIn.class,
            OwnerKeyDto.class, OwnerKeyDtoMixIn.class,
            FlowStageDto.class, FlowStageDtoMixIn.class,
            InputDependencyDto.class, InputDependencyDtoMixIn.class,
            InputProviderDto.class, InputProviderDtoMixIn.class
    );

    public static Map<Class<?>,Class<?>> STAGE_MIXINS = Map.of(
            StageUpdateEvent.class, StageUpdateEventMixIn.class,
            StageDto.class, StageDtoMixIn.class
    );

    public static Map<Class<?>,Class<?>> DEFINITION_MIXINS = Map.of(
            DefinitionUpdateEvent.class, DefinitionUpdateEventMixIn.class,
            StageDefinitionDto.class, StageDefinitionDtoMixIn.class,

            FsStageDto.class, FsStageDtoMixIn.class,
            IoDependencyDto.class, IoDependencyDtoMixIn.class,
            com.maukaim.bulo.runs.orchestrators.io.models.definition.InputProviderDto.class,
            com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.definition.InputProviderDtoMixIn.class,
            OutputProviderDto.class, OutputProviderDtoMixIn.class
    );


    public static Map<Class<?>, Class<?>> ORCHESTRATOR_SERIALIZATION_JACKSON_MIXIN = new HashMap<>() {{
        putAll(STAGE_RUN_EVENTS_MIXINS);
        putAll(FLOW_RUN_MIXINS);
        putAll(FLOW_MIXINS);
        putAll(STAGE_MIXINS);
        putAll(DEFINITION_MIXINS);
        put(FlowRunInstruction.class, TriggerEventMixIn.class);
        put(NeedStageRunCancellationEvent.class, NeedStageRunCancellationEventMixIn.class);
        put(NeedStageRunExecutionEvent.class, NeedStageRunExecutionEventMixIn.class);
    }};
}
