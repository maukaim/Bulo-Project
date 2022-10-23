package com.maukaim.bulo.runs.orchestrators.io.models;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.OrchestrableContextDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.OrchestrableContextStatusDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.ContextDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.RunContextTypeDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class FunctionalStageRunDto implements StageRunDto<OrchestrableContextStatusDto>, OrchestrableContextDto<String> {
    private final String stageRunId;
    private final ContextualizedStageId contextualizedStageId;
    private final ContextDto<?> context;
    private final OrchestrableContextStatusDto status;
    private final Set<StageRunDependencyDto> dependencies;
    private final Instant startTime;
    private final Instant endTime;
    private final ExecutionGraphDto executionGraph;
    private final Map<String, StageRunDto> stageRunByIds;

    public FunctionalStageRunDto(String stageRunId,
                                 ContextualizedStageId contextualizedStageId,
                                 ContextDto<?> context,
                                 OrchestrableContextStatusDto status,
                                 Set<StageRunDependencyDto> dependencies,
                                 Instant startTime,
                                 Instant endTime,
                                 ExecutionGraphDto executionGraph,
                                 Map<String, StageRunDto> stageRunByIds) {
        this.stageRunId = stageRunId;
        this.contextualizedStageId = contextualizedStageId;
        this.context = context;
        this.status = status;
        this.dependencies = dependencies;
        this.startTime = startTime;
        this.endTime = endTime;
        this.executionGraph = executionGraph;
        this.stageRunByIds = stageRunByIds;
    }

    @Override
    public RunContextTypeDto getContextType() {
        return RunContextTypeDto.FUNCTIONAL_STAGE_RUN;
    }

    @Override
    public String getContextId() {
        return stageRunId;
    }

    @Override
    public ExecutionGraphDto getExecutionGraph() {
        return executionGraph;
    }

    @Override
    public Map<String, StageRunDto> getStageRunByIds() {
        return stageRunByIds;
    }

    @Override
    public OrchestrableContextStatusDto getOrchestrableContextStatus() {
        return status;
    }

    @Override
    public StageType getStageType() {
        return StageType.TECHNICAL;
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
    public ContextDto getContext() {
        return context;
    }

    @Override
    public OrchestrableContextStatusDto getStatus() {
        return status;
    }


    @Override
    public Set<StageRunDependencyDto> getDependencies() {
        return dependencies;
    }

    @Override
    public Instant getStartTime() {
        return startTime;
    }

    @Override
    public Instant getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "TechnicalStageRunDto{" +
                "stageRunId='" + stageRunId + '\'' +
                ", contextualizedStageId=" + contextualizedStageId +
                ", context=" + context +
                ", stageRunStatus=" + status +
                ", dependencies=" + dependencies +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}