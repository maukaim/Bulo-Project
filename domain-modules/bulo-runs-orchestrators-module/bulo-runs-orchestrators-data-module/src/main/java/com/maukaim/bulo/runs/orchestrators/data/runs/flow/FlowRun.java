package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunStatus;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FlowRun {
    private final String flowRunId;
    private final String flowId;
    private final ExecutionGraph executionGraph;
    private final Map<String, StageRun> stageRunViewByIds;
    private final FlowRunStatus flowRunStatus;

    public FlowRun(String flowRunId, String flowId, ExecutionGraph executionGraph, Map<String, StageRun> stageRunViewByIds, FlowRunStatus flowRunStatus) {
        this.flowRunId = flowRunId;
        this.flowId = flowId;
        this.stageRunViewByIds = Map.copyOf(stageRunViewByIds);
        this.flowRunStatus = flowRunStatus;
        this.executionGraph = executionGraph;
    }

    public ExecutionGraph getExecutionGraph() {
        return this.executionGraph;
    }

    public String getFlowRunId() {
        return flowRunId;
    }

    public String getFlowId() {
        return flowId;
    }

    //TODO: StageRun should not be there, it should be in StageRunService, here only StageRunIds !!
    public Map<String, StageRun> getStageRunsById() {
        return stageRunViewByIds;
    }

    public FlowRunStatus getFlowRunStatus() {
        return flowRunStatus;
    }

    public Set<String> getStageRunIds(){
        return this.stageRunViewByIds.keySet();
    }
    private boolean stageIsSuccessfullyTerminated(FlowStageId stageId) {
        Optional<StageRun> stageRunView = this.stageRunViewByIds.values().stream()
                .filter(stageRun -> stageId.equals(stageRun.getFlowStageId()))
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
