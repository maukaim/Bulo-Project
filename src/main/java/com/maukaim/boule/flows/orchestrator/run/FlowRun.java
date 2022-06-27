package com.maukaim.boule.flows.orchestrator.run;

import com.maukaim.boule.flows.orchestrator.flow.model.ExecutionGraph;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunStatus;
import com.maukaim.boule.flows.orchestrator.stage.run.StageRunView;
import com.maukaim.boule.flows.orchestrator.util.RecordLockable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableSet;

/**
 * Represent a run memory.
 * - What is the next step?
 * - Is the RUn terminated?
 * - Can I start to run?
 */
public class FlowRun implements RecordLockable<ReentrantLock> {
    private final String flowRunId;
    private final String flowId;
    private final ExecutionGraph executionGraph;
    private final Map<String, StageRunView> stageRunViewByStageIds;
    private final FlowRunStatus flowRunStatus;

    public FlowRun(String flowRunId, String flowId, ExecutionGraph executionGraph) {
        this(flowRunId, flowId, executionGraph, new HashMap<>(), FlowRunStatus.NEW);
    }

    public FlowRun(String runId, String flowId, ExecutionGraph executionGraph, Map<String, StageRunView> stageRunViewByStageIds, FlowRunStatus flowRunStatus) {
        this.flowRunId = runId;
        this.flowId = flowId;
        this.stageRunViewByStageIds = Map.copyOf(stageRunViewByStageIds);
        this.flowRunStatus = flowRunStatus;
        this.executionGraph = executionGraph;

        this.entityLock = new ReentrantLock();
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

    public Map<String, StageRunView> getViewByStageId() {
        return stageRunViewByStageIds;
    }

    public FlowRunStatus getFlowRunStatus() {
        return flowRunStatus;
    }

    private boolean stageIsSuccessfullyTerminated(String stageId) {
        StageRunView stageRunView = this.stageRunViewByStageIds.get(stageId);
        return stageRunView != null && StageRunStatus.SUCCESS.equals(stageRunView.getStageRunStatus());
    }

    public boolean allRunsAreTerminated() {
        return this.stageRunViewByStageIds.entrySet().stream()
                .filter(entry -> entry.getValue().isTerminated())
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet()).containsAll(this.executionGraph.getAllStageIds());
    }

    public Set<String> getRunningStages(){
        return this.getViewByStageId().entrySet().stream()
                .filter(entry-> !entry.getValue().isTerminated())
                .map(Map.Entry::getKey)
                .collect(toUnmodifiableSet());
    }

    @Override
    public String toString() {
        return "FlowRun{" +
                "flowRunId='" + flowRunId + '\'' +
                ", flowId='" + flowId + '\'' +
                ", executionGraph=" + executionGraph +
                ", resultByStageIds=" + stageRunViewByStageIds +
                ", flowRunStatus=" + flowRunStatus +
                '}';
    }


    private final ReentrantLock entityLock;
    @Override
    public ReentrantLock getEntityLock() {
        return entityLock;
    }

    public boolean otherAncestorsAreSuccessful(String stageId, String ancestorExcluded) {
        return this.executionGraph.getAncestors(stageId).stream()
                .filter(ancestor-> !ancestorExcluded.equals(ancestor))
                .allMatch(this::stageIsSuccessfullyTerminated);
    }
}
