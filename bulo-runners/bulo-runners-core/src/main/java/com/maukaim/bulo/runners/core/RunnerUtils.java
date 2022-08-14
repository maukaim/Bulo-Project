package com.maukaim.bulo.runners.core;

import com.maukaim.bulo.executor.core.api.models.TechnicalStageInputDefinition;
import com.maukaim.bulo.executor.core.api.models.TechnicalStageOutputDefinition;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class RunnerUtils {

    public static Map<String, TechnicalStageInputDefinition> toMap(TechnicalStageInputDefinition... technicalStageInputDefinitions){
        return Arrays.stream(technicalStageInputDefinitions)
                .collect(Collectors.toMap(
                        inputDefinition -> inputDefinition.getName(),
                        inputDefinition -> inputDefinition)
                );
    }

    public static Map<String, TechnicalStageOutputDefinition> toMap(TechnicalStageOutputDefinition... technicalStageOutputDefinitions){
        return Arrays.stream(technicalStageOutputDefinitions)
                .collect(Collectors.toMap(
                        outputDefinition -> outputDefinition.getName(),
                        outputDefinition -> outputDefinition)
                );
    }
}
