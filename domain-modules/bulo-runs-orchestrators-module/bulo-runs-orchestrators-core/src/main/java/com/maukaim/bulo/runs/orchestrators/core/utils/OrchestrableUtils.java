package com.maukaim.bulo.runs.orchestrators.core.utils;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.definition.OutputProvider;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextStageAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class OrchestrableUtils {

    public static Set<RunDependency> getRunDependencies(ContextStageId contextStageId,
                                                        OrchestrableRunContext<?> orchestrableRunContext,
                                                        Set<StageRun> previousRuns) {
        Set<RunDependency> result = new HashSet<>();

        Set<ContextualizedStageDependency> flowStageDependencies = orchestrableRunContext.getExecutionGraph().getFlowStageDependencies(contextStageId);
        for (ContextualizedStageDependency contextualizedStageDependency : flowStageDependencies) {
            String inputId = contextualizedStageDependency.getInputId();
            Set<ContextStageAncestor> flowStageAncestors = contextualizedStageDependency.getAncestors();
            Set<StageRunAncestor> stageRunAncestors = getStageRunAncestors(flowStageAncestors, previousRuns);
            result.add(new RunDependency(inputId, stageRunAncestors));
        }

        return result;
    }

    private static Set<StageRunAncestor> getStageRunAncestors(Set<ContextStageAncestor> contextStageAncestors,
                                                              Set<StageRun> previousRuns) {
        if (contextStageAncestors == null || contextStageAncestors.isEmpty()) {
            return Set.of();
        }

        Map<ContextStageId, StageRun> alreadyRanFlowStageIdByStageRunIds = previousRuns.stream()
                .collect(toMap(
                        stageRun -> stageRun.getContextStageId(),
                        stageRun -> stageRun
                ));
        return contextStageAncestors.stream()
                .map(contextStageAncestor -> {
                    StageRun actualAncestorRun = alreadyRanFlowStageIdByStageRunIds.get(contextStageAncestor.getContextStageId());
                    if(actualAncestorRun instanceof TechnicalStageRun){
                        return List.of(new StageRunAncestor(
                                actualAncestorRun.getStageRunId(),
                                contextStageAncestor.getOutputIds())
                        );
                    }else if(actualAncestorRun instanceof FunctionalStageRun){
                        return getAncestors(contextStageAncestor.getOutputIds(), (FunctionalStageRun) actualAncestorRun);
                    }else{
                        throw new RuntimeException("StageRun type not supported: " + actualAncestorRun.getClass().getName());
                    }
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private static List<StageRunAncestor> getAncestors(Set<String> functionalStageExpectedOutputs, FunctionalStageRun functionalStageRun) {

        Map<ContextStageId, StageRun> runMap = functionalStageRun.getAllStageRuns().stream()
                .collect(toMap(
                        stageRun -> stageRun.getContextStageId(),
                        stageRun -> stageRun
                ));

        List<StageRunAncestor> stageRunAncestors = new ArrayList<>();
        for (OutputProvider outputProvider : functionalStageRun.getOutputProviders()) {
            StageRun stageRun = runMap.get(outputProvider.getContextStageId());
            if(stageRun == null){
                throw new RuntimeException("No Run available for contextStageId" + outputProvider.getContextStageId());
            }
            Set<String> providedOutputs = outputProvider.getOutputIds().stream()
                    .filter(outputId -> functionalStageExpectedOutputs.contains(outputId))
                    .collect(Collectors.toSet());
            if(!providedOutputs.isEmpty()){
                stageRunAncestors.add(new StageRunAncestor(stageRun.getStageRunId(), providedOutputs));
            }
        }

        return stageRunAncestors;
    }
}

//TODO: For now, does not work recursively. If outputProvider of FS is a FS, fail.