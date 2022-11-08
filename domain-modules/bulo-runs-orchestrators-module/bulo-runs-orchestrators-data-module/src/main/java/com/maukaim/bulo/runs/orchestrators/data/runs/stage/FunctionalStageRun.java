package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.runs.orchestrators.data.RunContextType;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class FunctionalStageRun extends OrchestrableRunContext<String> implements StageRun<OrchestrableContextStatus> {
    private final String stageRunId;
    private final ContextualizedStageId contextualizedStageId;
    private final RunContext<?> runContext;
    private final OrchestrableContextStatus status;
    private final Set<RunDependency> stageRunDependencies;
    private final Instant startTime;
    private final Instant endTime;

    private final ExecutionGraph executionGraph;
    private final Map<String, StageRun> stageRunViewByIds;

    public FunctionalStageRun(String stageRunId,
                              ContextualizedStageId contextualizedStageId,
                              RunContext<?> runContext,
                              OrchestrableContextStatus status,
                              Set<RunDependency> stageRunDependencies,
                              Instant startTime,
                              Instant endTime,
                              ExecutionGraph executionGraph,
                              Map<String, StageRun> stageRunViewByIds) {
        this.stageRunId = stageRunId;
        this.contextualizedStageId = contextualizedStageId;
        this.runContext = runContext;
        this.status = status;
        this.stageRunDependencies = stageRunDependencies;
        this.startTime = startTime;
        this.endTime = endTime;
        this.executionGraph = executionGraph;
        this.stageRunViewByIds = stageRunViewByIds;
    }

    @Override
    public RunContextType getContextType() {
        return RunContextType.FUNCTIONAL_STAGE_RUN;
    }

    @Override
    public String getContextId() {
        return stageRunId;
    }

    @Override
    public ExecutionGraph getExecutionGraph() {
        return executionGraph;
    }

    @Override
    public Map<String, StageRun> getStageRunsById() {
        return stageRunViewByIds;
    }

    @Override
    public StageType getStageType() {
        return StageType.FUNCTIONAL;
    }

    @Override
    public String getStageRunId() {
        return stageRunId;
    }

    @Override
    public ContextualizedStageId getContextualizedStageId() {
        return contextualizedStageId;
    }

    @Override
    public RunContext<?> getContext() {
        return runContext;
    }

    @Override
    public OrchestrableContextStatus getStatus() {
        return status;
    }


    @Override
    public Set<RunDependency> getStageRunDependencies() {
        return stageRunDependencies;
    }

    @Override
    public Instant getEndTime() {
        return endTime;
    }

    @Override
    public Instant getStartTime() {
        return startTime;
    }

    @Override
    public RunContext<String> toRunContext() {
        return new FunctionalStageRunContext(getContextId(), getStageRunDependencies());
    }

    @Override
    public String toString() {
        return "FunctionalStageRun{" +
                "stageRunId='" + stageRunId + '\'' +
                ", contextualizedStageId=" + contextualizedStageId +
                ", runContext=" + runContext +
                ", status=" + status +
                ", stageRunDependencies=" + stageRunDependencies +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", executionGraph=" + executionGraph +
                ", stageRunViewByIds=" + stageRunViewByIds +
                '}';
    }
}
