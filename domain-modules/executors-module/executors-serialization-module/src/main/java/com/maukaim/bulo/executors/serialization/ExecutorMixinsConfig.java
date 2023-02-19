package com.maukaim.bulo.executors.serialization;

import com.maukaim.bulo.executors.serialization.mixins.definitions.ParameterDefinitionDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.definitions.StageInputDefinitionDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.definitions.StageOutputDefinitionDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.results.StageRunResultDtoMixIn;
import com.maukaim.bulo.executors.serialization.mixins.results.StageRunResultEventMixIn;
import com.maukaim.bulo.executors.serialization.mixins.runs.AcknowledgeStageRunEventMixIn;
import com.maukaim.bulo.executors.serialization.mixins.runs.StageRunEventMixIn;
import com.maukaim.bulo.executors.serialization.mixins.runs.StandardStageRunEventMixIn;
import com.maukaim.bulo.io.definitions.client.dtos.ParameterDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageInputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageOutputDefinitionDto;
import com.maukaim.bulo.io.executors.system.AcknowledgeStageRunEvent;
import com.maukaim.bulo.io.executors.system.RunCancelledStageRunEvent;
import com.maukaim.bulo.io.executors.system.RunFailedStageRunEvent;
import com.maukaim.bulo.io.executors.system.RunSuccessfulStageRunEvent;
import com.maukaim.bulo.io.executors.system.StageRunEvent;
import com.maukaim.bulo.io.executors.system.StageRunResultEvent;
import com.maukaim.bulo.io.executors.system.StartRunStageRunEvent;
import com.maukaim.bulo.io.executors.system.dtos.StageRunResultDto;

import java.util.HashMap;
import java.util.Map;

public class ExecutorMixinsConfig {

    private static Map<Class<?>, Class<?>> RESULTS_JACKSON_MIXIN = Map.of(
            StageRunResultEvent.class, StageRunResultEventMixIn.class,
            StageRunResultDto.class, StageRunResultDtoMixIn.class
    );

    private static Map<Class<?>, Class<?>> STAGE_RUNS_JACKSON_MIXIN = Map.of(
            AcknowledgeStageRunEvent.class, AcknowledgeStageRunEventMixIn.class,
            RunCancelledStageRunEvent.class, StandardStageRunEventMixIn.class,
            RunFailedStageRunEvent.class, StandardStageRunEventMixIn.class,
            RunSuccessfulStageRunEvent.class, StandardStageRunEventMixIn.class,
            StartRunStageRunEvent.class, StandardStageRunEventMixIn.class,
            StageRunEvent.class, StageRunEventMixIn.class
    );

    private static Map<Class<?>, Class<?>> DEFINITIONS_JACKSON_MIXIN = Map.of(
            StageInputDefinitionDto.class, StageInputDefinitionDtoMixIn.class,
            StageOutputDefinitionDto.class, StageOutputDefinitionDtoMixIn.class,
            ParameterDefinitionDto.class, ParameterDefinitionDtoMixIn.class
    );

    public static Map<Class<?>, Class<?>> EXECUTORS_SERVICE_JACKSON_MIXIN = new HashMap<>() {{
        putAll(RESULTS_JACKSON_MIXIN);
        putAll(STAGE_RUNS_JACKSON_MIXIN);
        putAll(DEFINITIONS_JACKSON_MIXIN);
    }};
}
