package com.maukaim.boule.flows.orchestrator.flow.run;

import com.maukaim.boule.flows.orchestrator.flow.view.ExecutionGraph;
import com.maukaim.boule.flows.orchestrator.flow.view.FlowStageId;
import com.maukaim.boule.flows.orchestrator.stage.run.model.StageRunStatus;
import com.maukaim.boule.flows.orchestrator.stage.run.model.StageRunView;
import com.maukaim.boule.flows.orchestrator.util.RecordLockable;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * Represent a run memory.
 * - What is the next step?
 * - Is the RUn terminated?
 * - Can I start to run?
 */
public class FlowRun implements RecordLockable<ReentrantLock> {
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

        this.entityLock = new ReentrantLock();
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

    public Set<StageRunView> getInFlightStageRuns(){
        return this.getStageRunsById().values().stream()
                .filter(stageRunView-> !stageRunView.isTerminated())
                .collect(Collectors.toUnmodifiableSet());
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


    private final ReentrantLock entityLock;
    @Override
    public ReentrantLock getEntityLock() {
        return entityLock;
    }

    public boolean otherAncestorsAreSuccessful(FlowStageId stageId, FlowStageId ancestorExcludedId) {
        return this.executionGraph.getAncestors(stageId).stream()
                .filter(ancestor-> !ancestorExcludedId.equals(ancestor))
                .allMatch(this::stageIsSuccessfullyTerminated);
    }
}
