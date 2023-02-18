package com.maukaim.bulo.io.runs.orchestrators.models;

import com.maukaim.bulo.io.definitions.client.models.functional.OutputProviderDto;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.OrchestrableContextStatusDto;
import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.RunContextDto;
import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.RunContextTypeDto;
import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.StageRunDependencyDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.OrchestrableContextDto;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class FunctionalStageRunDto implements StageRunDto<OrchestrableContextStatusDto>, OrchestrableContextDto<String> {
    private final String stageRunId;
    private final ContextStageId contextStageId;
    private final RunContextDto<?> context;
    private final OrchestrableContextStatusDto status;
    private final Set<StageRunDependencyDto> dependencies;
    private final Instant startTime;
    private final Instant endTime;
    private final Set<OutputProviderDto> outputProviders;
    private final ExecutionGraphDto executionGraph;
    private final Map<String, StageRunDto<?>> stageRunByIds;

    public FunctionalStageRunDto(String stageRunId,
                                 ContextStageId contextStageId,
                                 RunContextDto<?> context,
                                 OrchestrableContextStatusDto status,
                                 Set<StageRunDependencyDto> dependencies,
                                 Instant startTime,
                                 Instant endTime,
                                 Set<OutputProviderDto> outputProviders,
                                 ExecutionGraphDto executionGraph,
                                 Map<String, StageRunDto<?>> stageRunByIds) {
        this.stageRunId = stageRunId;
        this.contextStageId = contextStageId;
        this.context = context;
        this.status = status;
        this.dependencies = dependencies;
        this.startTime = startTime;
        this.endTime = endTime;
        this.outputProviders = outputProviders;
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
    public Map<String, StageRunDto<?>> getStageRunByIds() {
        return stageRunByIds;
    }

    public Set<OutputProviderDto> getOutputProviders() {
        return outputProviders;
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
    public ContextStageId getContextualizedStageId() {
        return contextStageId;
    }

    @Override
    public RunContextDto<?> getContext() {
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
                ", contextualizedStageId=" + contextStageId +
                ", context=" + context +
                ", stageRunStatus=" + status +
                ", dependencies=" + dependencies +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}