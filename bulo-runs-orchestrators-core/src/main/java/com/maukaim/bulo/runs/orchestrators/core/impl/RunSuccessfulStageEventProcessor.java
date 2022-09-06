package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.core.FlowService;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowStageAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowStageDependency;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;
import com.maukaim.bulo.runs.orchestrators.io.events.RunSuccessfulStageRunEvent;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.core.factories.StageRunFactory;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class RunSuccessfulStageEventProcessor extends StageEventProcessor<RunSuccessfulStageRunEvent> {
    private final StageRunService stageRunService;

    public RunSuccessfulStageEventProcessor(FlowRunService flowRunService, StageRunService stageRunService) {
        super(flowRunService);
        this.stageRunService = stageRunService;
    }

    @Override
    public Class<RunSuccessfulStageRunEvent> getExpectedStageEventClass() {
        return RunSuccessfulStageRunEvent.class;
    }

    @Override
    public void process(RunSuccessfulStageRunEvent event, String flowRunId) {
        System.out.println("Received Successful run Event, will proceed -> " + event);
        this.flowRunService.computeStageRunViewUnderLock(flowRunId, (actualFlowRun) -> {
            StageRun actualStageRun = actualFlowRun.getStageRunsById().get(event.getStageRunId());
            if (actualStageRun == null) {
                throw new IllegalArgumentException("This stage id was not requested to run under this flowRun");
            }

            Map<String, StageRun> result = new HashMap<>();
            result.put(event.getStageRunId(), StageRunFactory.success(actualStageRun, event.getInstant()));

            Set<FlowStageId> childrenIds = actualFlowRun.getExecutionGraph().getChildren(actualStageRun.getFlowStageId());
            if(!actualFlowRun.getFlowRunStatus().isTerminal() && !childrenIds.isEmpty()){
                    result.putAll(this.requestRunIfAuthorized(actualFlowRun, actualStageRun.getFlowStageId(), childrenIds));
            }

            return result;
        });
    }

    private Map<String, StageRun> requestRunIfAuthorized(FlowRun flowRun, FlowStageId currentStageId, Set<FlowStageId> children) {
        String flowRunId = flowRun.getFlowRunId();
        Map<FlowStageId, Set<StageRunDependency>> childrenToStart = children.stream()
                .filter(childStageId -> flowRun.otherAncestorsAreSuccessful(childStageId, currentStageId))
                .collect(toMap(
                        flowStageId -> flowStageId,
                        flowStageId -> this.getDependencies(flowStageId, flowRun)
                        ));
        return this.stageRunService.startRuns(flowRunId, childrenToStart);
    }

    //TODO: tout ca devrait sortir de la class non?
    private Set<StageRunDependency> getDependencies(FlowStageId flowStageId, FlowRun flowRun) {
        Set<StageRunDependency> result = new HashSet<>();

        Set<FlowStageDependency> flowStageDependencies = flowRun.getExecutionGraph().getFlowStageDependencies(flowStageId);
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
        if(flowStageAncestors == null || flowStageAncestors.isEmpty()){
            return Set.of();
        }

        Map<FlowStageId, String> alreadyRanFlowStageIdByStageRunIds = flowRun.getStageRunIds().stream()
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
