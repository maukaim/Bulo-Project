package com.maukaim.bulo.runs.orchestrators.serialization;

import com.maukaim.bulo.io.definitions.client.models.functional.OutputProviderDto;
import com.maukaim.bulo.io.runs.orchestrators.events.AcknowledgeRequestStageRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.BasicStageRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.DefinitionUpdateEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.FlowEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.FlowRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.FlowRunStartEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.NeedStageRunCancellationEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.NeedStageRunExecutionEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.RunCancelledStageRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.RunFailedStageRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.RunSuccessfulStageRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.StageUpdateEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.StartRunStageRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.models.definition.InputProviderDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.FlowDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.FlowStageDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.InputDependencyDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.OwnerKeyDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.FlowRunDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.FlowRunStageDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.FlowStageDependencyDto;
import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.StageRunAncestorDto;
import com.maukaim.bulo.io.runs.orchestrators.models.FunctionalStageRunDto;
import com.maukaim.bulo.io.runs.orchestrators.models.TechnicalStageRunDto;
import com.maukaim.bulo.io.runs.orchestrators.models.definition.FsStageDto;
import com.maukaim.bulo.io.runs.orchestrators.models.definition.IoDependencyDto;
import com.maukaim.bulo.io.runs.orchestrators.models.definition.StageDefinitionDto;
import com.maukaim.bulo.io.runs.orchestrators.models.stage.StageDto;
import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.StageRunDependencyDto;
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
            com.maukaim.bulo.io.runs.orchestrators.models.flowrun.StageRunAncestorDto.class, FlowStageAncestorDtoMixIn.class,
            FlowStageDependencyDto.class, FlowStageDependencyDtoMixIn.class,
            TechnicalStageRunDto.class, TechnicalStageRunDtoMixIn.class,
            FunctionalStageRunDto.class, FunctionalStageRunDtoMixIn.class,
            StageRunDependencyDto.class, StageRunDependencyDtoMixIn.class,
            StageRunAncestorDto.class, StageRunAncestorDtoMixIn.class
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
            com.maukaim.bulo.io.runs.orchestrators.models.flow.InputProviderDto.class, InputProviderDtoMixIn.class
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
            InputProviderDto.class,
            com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.definition.InputProviderDtoMixIn.class,
            OutputProviderDto.class, OutputProviderDtoMixIn.class
    );

    public static Map<Class<?>, Class<?>> ORCHESTRATOR_SERIALIZATION_JACKSON_MIXIN = new HashMap<>() {{
        putAll(STAGE_RUN_EVENTS_MIXINS);
        putAll(FLOW_RUN_MIXINS);
        putAll(FLOW_MIXINS);
        putAll(STAGE_MIXINS);
        putAll(DEFINITION_MIXINS);
        put(FlowRunStartEvent.class, TriggerEventMixIn.class);
        put(NeedStageRunCancellationEvent.class, NeedStageRunCancellationEventMixIn.class);
        put(NeedStageRunExecutionEvent.class, NeedStageRunExecutionEventMixIn.class);
    }};
}
