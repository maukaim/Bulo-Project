package com.maukaim.bulo.executors.serialization;

import com.maukaim.bulo.executors.serialization.mixins.definitions.ParameterDefinitionDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.definitions.StageInputDefinitionDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.definitions.StageOutputDefinitionDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.results.StageRunResultDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.results.StageRunResultEventMixIn;
import com.maukaim.bulo.executors.serialization.mixins.runs.AcknowledgeStageRunEventMixIn;
import com.maukaim.bulo.executors.serialization.mixins.runs.NeedStageRunCancelEventMixIn;
import com.maukaim.bulo.executors.serialization.mixins.runs.NeedStageRunEventMixIn;
import com.maukaim.bulo.executors.serialization.mixins.runs.StageRunAncestorDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.runs.StageRunDependencyDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.runs.StandardStageRunEventMixIn;
import com.maukaim.bulo.executors.serialization.mixins.stages.ParameterDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.stages.StageUpdateEventMixIn;
import com.maukaim.bulo.executors.serialization.mixins.stages.TechnicalStageDtoMixIn;
import com.maukaim.bulo.io.definitions.client.dtos.ParameterDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageInputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageOutputDefinitionDto;
import com.maukaim.bulo.io.executors.system.in.CancelRunInstruction;
import com.maukaim.bulo.io.executors.system.in.RunInstruction;
import com.maukaim.bulo.io.executors.system.in.StageUpdateEvent;
import com.maukaim.bulo.io.executors.system.in.model.ParameterDto;
import com.maukaim.bulo.io.executors.system.in.model.StageRunAncestorDto;
import com.maukaim.bulo.io.executors.system.in.model.StageRunDependencyDto;
import com.maukaim.bulo.io.executors.system.in.model.TechnicalStageDto;
import com.maukaim.bulo.io.executors.system.out.AcknowledgeStageRunEvent;
import com.maukaim.bulo.io.executors.system.out.RunCancelledStageRunEvent;
import com.maukaim.bulo.io.executors.system.out.RunFailedStageRunEvent;
import com.maukaim.bulo.io.executors.system.out.RunSuccessfulStageRunEvent;
import com.maukaim.bulo.io.executors.system.out.StageRunResultEvent;
import com.maukaim.bulo.io.executors.system.out.StartRunStageRunEvent;
import com.maukaim.bulo.io.executors.system.out.model.StageRunResultDto;

import java.util.HashMap;
import java.util.Map;

public class MixinsConfig {

    private static Map<Class<?>, Class<?>> RESULTS_JACKSON_MIXIN = Map.of(
            StageRunResultEvent.class, StageRunResultEventMixIn.class,
            StageRunResultDto.class, StageRunResultDtoMixIn.class
    );

    private static Map<Class<?>, Class<?>> STAGES_JACKSON_MIXIN = Map.of(
            StageUpdateEvent.class, StageUpdateEventMixIn.class,
            TechnicalStageDto.class, TechnicalStageDtoMixIn.class,
            ParameterDto.class, ParameterDtoMixIn.class
    );

    private static Map<Class<?>, Class<?>> STAGE_RUNS_JACKSON_MIXIN = Map.of(
            AcknowledgeStageRunEvent.class, AcknowledgeStageRunEventMixIn.class,
            RunCancelledStageRunEvent.class, StandardStageRunEventMixIn.class,
            RunFailedStageRunEvent.class, StandardStageRunEventMixIn.class,
            RunSuccessfulStageRunEvent.class, StandardStageRunEventMixIn.class,
            StartRunStageRunEvent.class, StandardStageRunEventMixIn.class,
            RunInstruction.class, NeedStageRunEventMixIn.class,
            CancelRunInstruction.class, NeedStageRunCancelEventMixIn.class,
            StageRunAncestorDto.class, StageRunAncestorDtoMixIn.class,
            StageRunDependencyDto.class, StageRunDependencyDtoMixIn.class
    );

    private static Map<Class<?>, Class<?>> DEFINITIONS_JACKSON_MIXIN = Map.of(
            StageInputDefinitionDto.class, StageInputDefinitionDtoMixIn.class,
            StageOutputDefinitionDto.class, StageOutputDefinitionDtoMixIn.class,
            ParameterDefinitionDto.class, ParameterDefinitionDtoMixIn.class
    );

    public static Map<Class<?>, Class<?>> EXECUTORS_SERVICE_JACKSON_MIXIN = new HashMap<>() {{
        putAll(RESULTS_JACKSON_MIXIN);
        putAll(STAGES_JACKSON_MIXIN);
        putAll(STAGE_RUNS_JACKSON_MIXIN);
        putAll(DEFINITIONS_JACKSON_MIXIN);
    }};
}
