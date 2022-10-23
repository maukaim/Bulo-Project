package com.maukaim.bulo.runs.orchestrators.serialization;

import com.maukaim.bulo.runs.orchestrators.io.events.*;
import com.maukaim.bulo.runs.orchestrators.io.models.TechnicalStageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.*;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.*;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunAncestorDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.events.*;
import com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.*;

import java.util.HashMap;
import java.util.Map;

public class MixinsConfig {

    public static Map<Class<?>, Class<?>> FLOW_RUN_MIXINS = Map.of(
            FlowRunEvent.class, FlowRunEventMixIn.class,
            FlowRunDto.class, FlowRunDtoMixIn.class,
            ExecutionGraphDto.class, ExecutionGraphDtoMixIn.class,
            FlowRunStageDto.class, FlowRunStageDtoMixIn.class,
            FlowStageAncestorDto.class, FlowStageAncestorDtoMixIn.class,
            FlowStageDependencyDto.class, FlowStageDependencyDtoMixIn.class,
            TechnicalStageRunDto.class, StageRunDtoMixIn.class,
            StageRunDependencyDto.class, StageRunDependencyDtoMixIn.class,
            StageRunAncestorDto.class, StageRunAncestorDtoMixIn.class
    );

    public static Map<Class<?>, Class<?>> STAGE_RUN_EVENTS_MIXINS = Map.of(
            AcknowledgeRequestStageRunEvent.class, AcknowledgeRequestStageRunEventMixIn.class,
            RunCancelledStageRunEvent.class, StandardStageRunEventMixin.class,
            RunFailedStageRunEvent.class, StandardStageRunEventMixin.class,
            RunSuccessfulStageRunEvent.class, StandardStageRunEventMixin.class,
            StartRunStageRunEvent.class, StandardStageRunEventMixin.class,
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

    public static Map<Class<?>, Class<?>> ORCHESTRATOR_SERIALIZATION_JACKSON_MIXIN = new HashMap<>() {{
        putAll(STAGE_RUN_EVENTS_MIXINS);
        putAll(FLOW_RUN_MIXINS);
        putAll(FLOW_MIXINS);
        put(FlowRunInstruction.class, TriggerEventMixIn.class);
        put(NeedStageRunCancellationEvent.class, NeedStageRunCancellationEventMixIn.class);
        put(NeedStageRunExecutionEvent.class, NeedStageRunExecutionEventMixIn.class);
    }};
}
