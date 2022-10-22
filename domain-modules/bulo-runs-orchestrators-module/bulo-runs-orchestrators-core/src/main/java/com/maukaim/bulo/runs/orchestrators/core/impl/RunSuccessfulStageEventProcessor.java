package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.factories.StageRunFactory;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowStageAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowStageDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class RunSuccessfulStageEventProcessor extends StageEventProcessor {
    private final StageRunService stageRunService;

    public RunSuccessfulStageEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        super(flowRunService);
        this.stageRunService = stageRunService;
    }

    public void process(String stageRunId, Instant instant, String flowRunId) {
        System.out.println("Successful Run event... " + stageRunId);

        this.flowRunService.computeStageRunViewUnderLock(flowRunId, (actualFlowRun) -> {
            StageRun actualStageRun = actualFlowRun.getStageRunsById().get(stageRunId);
            if (actualStageRun == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }

            Map<String, StageRun> result = new HashMap<>();
            result.put(stageRunId, StageRunFactory.success(actualStageRun, instant));

            Set<ContextualizedStageId> childrenIds = actualFlowRun.getExecutionGraph().getChildren(actualStageRun.getFlowStageId());
            if (!actualFlowRun.getFlowRunStatus().isTerminal() && !childrenIds.isEmpty()) {
                result.putAll(this.getStageRunsToBeRequestedIfAuthorized(actualFlowRun, actualStageRun.getFlowStageId(), childrenIds));
            }

            return result;
        });
    }

    private Map<String, StageRun> getStageRunsToBeRequestedIfAuthorized(FlowRun flowRun, ContextualizedStageId currentStageId, Set<ContextualizedStageId> children) {
        String flowRunId = flowRun.getFlowRunId();
        Map<ContextualizedStageId, Set<StageRunDependency>> childrenToStart = children.stream()
                .filter(childStageId -> flowRun.otherAncestorsAreSuccessful(childStageId, currentStageId))
                .collect(toMap(
                        flowStageId -> flowStageId,
                        flowStageId -> this.getDependencies(flowStageId, flowRun)
                ));
        return this.stageRunService.getNextStageRun(flowRunId, childrenToStart);
    }

    //TODO: tout ca devrait sortir de la class non?
    private Set<StageRunDependency> getDependencies(ContextualizedStageId contextualizedStageId, FlowRun flowRun) {
        Set<StageRunDependency> result = new HashSet<>();

        Set<FlowStageDependency> flowStageDependencies = flowRun.getExecutionGraph().getFlowStageDependencies(contextualizedStageId);
        for (FlowStageDependency flowStageDependency : flowStageDependencies) {
            String inputId = flowStageDependency.getInputId();
            Set<FlowStageAncestor> flowStageAncestors = flowStageDependency.getAncestors();
            Set<StageRunAncestor> stageRunAncestors = getStageRunAncestors(flowStageAncestors, flowRun);
            result.add(new StageRunDependency(inputId, stageRunAncestors));
        }

        return result;
    }

    //TODO: tout ca devrait sortir de la class non?
    private Set<StageRunAncestor> getStageRunAncestors(Set<FlowStageAncestor> flowStageAncestors, FlowRun flowRun) {
        if (flowStageAncestors == null || flowStageAncestors.isEmpty()) {
            return Set.of();
        }

        Map<ContextualizedStageId, String> alreadyRanFlowStageIdByStageRunIds = flowRun.getStageRunIds().stream()
                .map(this.stageRunService::getById)
                .filter(Objects::nonNull)
                .collect(toMap(
                        stageRun -> stageRun.getFlowStageId(),
                        stageRun -> stageRun.getStageRunId()
                ));
        return flowStageAncestors.stream()
                .map(flowStageAncestor -> new StageRunAncestor(
                        alreadyRanFlowStageIdByStageRunIds.get(flowStageAncestor.getFlowStageId()),
                        flowStageAncestor.getOutputIds()))
                .collect(Collectors.toSet());
    }

}
