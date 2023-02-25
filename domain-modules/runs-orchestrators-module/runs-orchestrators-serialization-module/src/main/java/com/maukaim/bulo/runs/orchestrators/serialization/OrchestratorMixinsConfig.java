package com.maukaim.bulo.runs.orchestrators.serialization;

import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunStartEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunCancellationEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunExecutionEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.events.StageUpdateEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.models.FunctionalStageRunDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.TechnicalStageRunDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.FlowRunDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.FlowRunStageDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.FlowStageDependencyDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stage.StageDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunAncestorDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunDependencyDto;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.events.FlowRunEventMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.events.NeedStageRunCancellationEventMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.events.NeedStageRunExecutionEventMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.events.StageUpdateEventMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.events.TriggerEventMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.ExecutionGraphDtoMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.FlowRunDtoMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.FlowRunStageDtoMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.FlowStageAncestorDtoMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.FlowStageDependencyDtoMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.FunctionalStageRunDtoMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.StageRunAncestorDtoMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.StageRunDependencyDtoMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.TechnicalStageRunDtoMixIn;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.stage.StageDtoMixIn;

import java.util.HashMap;
import java.util.Map;

public class OrchestratorMixinsConfig {

    public static Map<Class<?>, Class<?>> FLOW_RUN_MIXINS = Map.of(
            FlowRunEvent.class, FlowRunEventMixIn.class,
            FlowRunDto.class, FlowRunDtoMixIn.class,
            ExecutionGraphDto.class, ExecutionGraphDtoMixIn.class,
            FlowRunStageDto.class, FlowRunStageDtoMixIn.class,
            com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.StageRunAncestorDto.class, FlowStageAncestorDtoMixIn.class,
            FlowStageDependencyDto.class, FlowStageDependencyDtoMixIn.class,
            TechnicalStageRunDto.class, TechnicalStageRunDtoMixIn.class,
            FunctionalStageRunDto.class, FunctionalStageRunDtoMixIn.class,
            StageRunDependencyDto.class, StageRunDependencyDtoMixIn.class,
            StageRunAncestorDto.class, StageRunAncestorDtoMixIn.class
    );

    public static Map<Class<?>, Class<?>> STAGE_MIXINS = Map.of(
            StageUpdateEvent.class, StageUpdateEventMixIn.class,
            StageDto.class, StageDtoMixIn.class
    );

    public static Map<Class<?>, Class<?>> ORCHESTRATOR_SERIALIZATION_JACKSON_MIXIN = new HashMap<>() {{
        putAll(FLOW_RUN_MIXINS);
        putAll(STAGE_MIXINS);
        put(FlowRunStartEvent.class, TriggerEventMixIn.class);
        put(NeedStageRunCancellationEvent.class, NeedStageRunCancellationEventMixIn.class);
        put(NeedStageRunExecutionEvent.class, NeedStageRunExecutionEventMixIn.class);
    }};
}
