package com.maukaim.bulo.runners.core;

import com.maukaim.bulo.executor.core.api.models.StageInputDefinition;
import com.maukaim.bulo.executor.core.api.models.StageOutputDefinition;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class RunnerUtils {

    public static Map<String, StageInputDefinition> toMap(StageInputDefinition... stageInputDefinitions){
        return Arrays.stream(stageInputDefinitions)
                .collect(Collectors.toMap(
                        inputDefinition -> inputDefinition.getName(),
                        inputDefinition -> inputDefinition)
                );
    }

    public static Map<String, StageOutputDefinition> toMap(StageOutputDefinition... stageOutputDefinitions){
        return Arrays.stream(stageOutputDefinitions)
                .collect(Collectors.toMap(
                        outputDefinition -> outputDefinition.getName(),
                        outputDefinition -> outputDefinition)
                );
    }
}
