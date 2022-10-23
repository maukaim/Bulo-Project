package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.runs.orchestrators.data.RunContextType;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class FunctionalStageRun extends OrchestrableContext<String> implements StageRun<OrchestrableContextStatus> {
    private final String stageRunId;
    private final ContextualizedStageId contextualizedStageId;
    private final Context<?> context;
    private final OrchestrableContextStatus status;
    private final Set<StageRunDependency> stageRunDependencies;
    private final Instant startTime;
    private final Instant endTime;

    private final ExecutionGraph executionGraph;
    private final Map<String, StageRun> stageRunViewByIds;

    public FunctionalStageRun(String stageRunId,
                              ContextualizedStageId contextualizedStageId,
                              Context<?> context,
                              OrchestrableContextStatus status,
                              Set<StageRunDependency> stageRunDependencies,
                              Instant startTime,
                              Instant endTime,
                              ExecutionGraph executionGraph,
                              Map<String, StageRun> stageRunViewByIds) {
        this.stageRunId = stageRunId;
        this.contextualizedStageId = contextualizedStageId;
        this.context = context;
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
    public Context<?> getContext() {
        return context;
    }

    @Override
    public OrchestrableContextStatus getStatus() {
        return status;
    }


    @Override
    public Set<StageRunDependency> getStageRunDependencies() {
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
}
