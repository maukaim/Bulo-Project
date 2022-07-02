package com.maukaim.bulo.runs.orchestrator.flow.run;

import com.maukaim.bulo.runs.orchestrator.flow.view.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrator.flow.view.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunStatus;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunView;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FlowRun {
    private final String flowRunId;
    private final String flowId;
    private final ExecutionGraph<FlowStageId> executionGraph;
    private final Map<String, StageRunView> stageRunViewByIds;
    private final FlowRunStatus flowRunStatus;

    public FlowRun(String flowRunId, String flowId, ExecutionGraph<FlowStageId> executionGraph, Map<String, StageRunView> stageRunViewByIds, FlowRunStatus flowRunStatus) {
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

    public Map<String, StageRunView> getStageRunsById() {
        return stageRunViewByIds;
    }

    public FlowRunStatus getFlowRunStatus() {
        return flowRunStatus;
    }

    private boolean stageIsSuccessfullyTerminated(FlowStageId stageId) {
        Optional<StageRunView> stageRunView = this.stageRunViewByIds.values().stream()
                .filter(stv -> stageId.equals(stv.getFlowStageId()))
                .findFirst();
        return stageRunView.isPresent() && StageRunStatus.SUCCESS.equals(stageRunView.get().getStageRunStatus());
    }

    public boolean allRunsAreTerminated() {
        return this.stageRunViewByIds.values().stream().allMatch(StageRunView::isTerminated);
    }

    public Collection<StageRunView> getAllStageRuns() {
        return this.getStageRunsById().values();
    }

    public Set<StageRunView> getInFlightStageRuns() {
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
