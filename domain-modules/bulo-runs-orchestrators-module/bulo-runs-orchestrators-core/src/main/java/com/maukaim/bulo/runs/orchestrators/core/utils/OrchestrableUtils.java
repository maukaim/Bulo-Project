package com.maukaim.bulo.runs.orchestrators.core.utils;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.StageRunAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class OrchestrableUtils {

    public static Set<RunDependency> getRunDependencies(ContextualizedStageId contextualizedStageId,
                                                        OrchestrableRunContext<?> orchestrableRunContext,
                                                        Set<StageRun> previousRuns) {
        Set<RunDependency> result = new HashSet<>();

        Set<ContextualizedStageDependency> flowStageDependencies = orchestrableRunContext.getExecutionGraph().getFlowStageDependencies(contextualizedStageId);
        for (ContextualizedStageDependency contextualizedStageDependency : flowStageDependencies) {
            String inputId = contextualizedStageDependency.getInputId();
            Set<StageRunAncestor> flowStageAncestors = contextualizedStageDependency.getAncestors();
            Set<com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor> stageRunAncestors = getStageRunAncestors(flowStageAncestors, previousRuns);
            result.add(new RunDependency(inputId, stageRunAncestors));
        }

        return result;
    }

    private static Set<com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor> getStageRunAncestors(Set<StageRunAncestor> stageRunAncestors,
                                                                                                                  Set<StageRun> previousRuns) {
        if (stageRunAncestors == null || stageRunAncestors.isEmpty()) {
            return Set.of();
        }

        Map<ContextualizedStageId, String> alreadyRanFlowStageIdByStageRunIds = previousRuns.stream()
                .collect(toMap(
                        stageRun -> stageRun.getContextualizedStageId(),
                        stageRun -> stageRun.getStageRunId()
                ));
        return stageRunAncestors.stream()
                .map(flowStageAncestor -> new com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor(
                        alreadyRanFlowStageIdByStageRunIds.get(flowStageAncestor.getFlowStageId()),
                        flowStageAncestor.getOutputIds()))
                .collect(Collectors.toSet());
    }

    /**
     * For output mapping., dans la map l.47, Faire en sorte que on agisse differement si le stageRun est une FS.
     * Dans ce cas, comput les stages ancestors en fonction de ca.
     * Comment faire le lien entre outputs et les outputs des leaves de la FS?
     * Puis, flatmap.
     */
}
