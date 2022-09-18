package com.maukaim.bulo.executors.serialization;

import com.maukaim.bulo.executors.io.in.NeedStageRunCancelEvent;
import com.maukaim.bulo.executors.io.in.NeedStageRunEvent;
import com.maukaim.bulo.executors.io.in.StageUpdateEvent;
import com.maukaim.bulo.executors.io.in.model.ParameterDto;
import com.maukaim.bulo.executors.io.in.model.StageRunAncestorDto;
import com.maukaim.bulo.executors.io.in.model.StageRunDependencyDto;
import com.maukaim.bulo.executors.io.in.model.TechnicalStageDto;
import com.maukaim.bulo.executors.io.out.*;
import com.maukaim.bulo.executors.io.out.model.ParameterDefinitionDto;
import com.maukaim.bulo.executors.io.out.model.StageInputDefinitionDto;
import com.maukaim.bulo.executors.io.out.model.StageOutputDefinitionDto;
import com.maukaim.bulo.executors.io.out.model.StageRunResultDto;
import com.maukaim.bulo.executors.serialization.mixins.definitions.ParameterDefinitionDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.definitions.StageDefinitionDeclarationEventMixIn;
import com.maukaim.bulo.executors.serialization.mixins.definitions.StageInputDefinitionDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.definitions.StageOutputDefinitionDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.results.StageRunResultDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.results.StageRunResultEventMixIn;
import com.maukaim.bulo.executors.serialization.mixins.runs.*;
import com.maukaim.bulo.executors.serialization.mixins.stages.ParameterDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.stages.StageUpdateEventMixIn;
import com.maukaim.bulo.executors.serialization.mixins.stages.TechnicalStageDtoMixIn;

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
            NeedStageRunEvent.class, NeedStageRunEventMixIn.class,
            NeedStageRunCancelEvent.class, NeedStageRunCancelEventMixIn.class,
            StageRunAncestorDto.class, StageRunAncestorDtoMixIn.class,
            StageRunDependencyDto.class, StageRunDependencyDtoMixIn.class
    );

    private static Map<Class<?>, Class<?>> DEFINITIONS_JACKSON_MIXIN = Map.of(
            StageDefinitionDeclarationEvent.class, StageDefinitionDeclarationEventMixIn.class,
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
