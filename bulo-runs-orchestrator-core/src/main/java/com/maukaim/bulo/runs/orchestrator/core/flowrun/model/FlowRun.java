package com.maukaim.bulo.runs.orchestrator.core.flowrun.model;

import com.maukaim.bulo.commons.core.models.FlowStageId;
import com.maukaim.bulo.commons.core.models.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRunStatus;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRun;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FlowRun {
    private final String flowRunId;
    private final String flowId;
    private final ExecutionGraph<FlowStageId> executionGraph;
    private final Map<String, StageRun> stageRunViewByIds;
    private final FlowRunStatus flowRunStatus;

    public FlowRun(String flowRunId, String flowId, ExecutionGraph<FlowStageId> executionGraph, Map<String, StageRun> stageRunViewByIds, FlowRunStatus flowRunStatus) {
        this.flowRunId = flowRunId;
        this.flowId = flowId;
        this.stageRunViewByIds = Map.copyOf(stageRunViewByIds);
        this.flowRunStatus = flowRunStatus;
        this.executionGraph = executionGraph;
    }

    public ExecutionGraph<FlowStageId> getExecutionGraph() {
        return this.executionGraph;
    }

    public String getFlowRunId() {
        return flowRunId;
    }

    public String getFlowId() {
        return flowId;
    }

    public Map<String, StageRun> getStageRunsById() {
        return stageRunViewByIds;
    }

    public FlowRunStatus getFlowRunStatus() {
        return flowRunStatus;
    }

    private boolean stageIsSuccessfullyTerminated(FlowStageId stageId) {
        Optional<StageRun> stageRunView = this.stageRunViewByIds.values().stream()
                .filter(stv -> stageId.equals(stv.getFlowStageId()))
                .findFirst();
        return stageRunView.isPresent() && StageRunStatus.SUCCESS.equals(stageRunView.get().getStageRunStatus());
    }

    public boolean allRunsAreTerminated() {
        return this.stageRunViewByIds.values().stream().allMatch(StageRun::isTerminated);
    }

    public Collection<StageRun> getAllStageRuns() {
        return this.getStageRunsById().values();
    }

    public Set<StageRun> getInFlightStageRuns() {
        return this.getStageRunsById().values().stream()
                .filter(stageRunView -> !stageRunView.isTerminated())
                .collect(Collectors.toUnmodifiableSet());
    }

    public boolean otherAncestorsAreSuccessful(FlowStageId stageId, FlowStageId ancestorExcludedId) {
        return this.executionGraph.getAncestors(stageId).stream()
                .filter(ancestor -> !ancestorExcludedId.equals(ancestor))
                .allMatch(this::stageIsSuccessfullyTerminated);
    }

    @Override
    public String toString() {
        return "FlowRun{" +
                "flowRunId='" + flowRunId + '\'' +
                ", flowId='" + flowId + '\'' +
                ", executionGraph=" + executionGraph +
                ", resultByStageIds=" + stageRunViewByIds +
                ", flowRunStatus=" + flowRunStatus +
                '}';
    }
}
