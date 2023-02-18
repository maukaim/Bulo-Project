package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage.impl;

import com.maukaim.bulo.io.definitions.client.models.functional.OutputProviderDto;
import com.maukaim.bulo.runs.orchestrators.data.definition.OutputProvider;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.ExecutionGraphDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage.OutputProviderDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage.StageRunDependencyDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage.StageRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.*;
import com.maukaim.bulo.io.runs.orchestrators.models.FunctionalStageRunDto;
import com.maukaim.bulo.io.runs.orchestrators.models.StageRunDto;
import com.maukaim.bulo.io.runs.orchestrators.models.TechnicalStageRunDto;
import com.maukaim.bulo.io.runs.orchestrators.models.StageRunStatusDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flowrun.OrchestrableContextStatusDto;
import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.RunContextDto;
import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.FlowRunContextDto;
import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.FunctionalStageRunContextDto;
import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.StageRunDependencyDto;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StageRunDtoAdapterImpl implements StageRunDtoAdapter {
    private final StageRunDependencyDtoAdapter stageRunDependencyDtoAdapter;
    private final ExecutionGraphDtoAdapter executionGraphDtoAdapter;
    private final OutputProviderDtoAdapter outputProviderDtoAdapter;

    public StageRunDtoAdapterImpl(StageRunDependencyDtoAdapter stageRunDependencyDtoAdapter,
                                  ExecutionGraphDtoAdapter executionGraphDtoAdapter,
                                  OutputProviderDtoAdapter outputProviderDtoAdapter) {
        this.stageRunDependencyDtoAdapter = stageRunDependencyDtoAdapter;
        this.executionGraphDtoAdapter = executionGraphDtoAdapter;
        this.outputProviderDtoAdapter = outputProviderDtoAdapter;
    }

    @Override
    public StageRunDto<?> adapte(StageRun<?> stageRun) {
        return switch (stageRun.getStageType()) {
            case TECHNICAL -> resolve((FunctionalStageRun)stageRun);
            case FUNCTIONAL -> resolve((TechnicalStageRun)stageRun);
        };
    }

    private FunctionalStageRunDto resolve(FunctionalStageRun stageRun) {
        return new FunctionalStageRunDto(
                stageRun.getStageRunId(),
                stageRun.getContextualizedStageId(),
                resolve(stageRun.getContext()),
                resolve(stageRun.getStatus()),
                resolve(stageRun.getStageRunDependencies()),
                stageRun.getStartTime(),
                stageRun.getEndTime(),
                resolveOutputProviders(stageRun.getOutputProviders()),
                resolve(stageRun.getExecutionGraph()),
                resolve(stageRun.getStageRunsById())
        );
    }

    private Set<OutputProviderDto> resolveOutputProviders(Set<OutputProvider> outputProviders) {
        return outputProviders == null ? Set.of() : outputProviders.stream()
                .map(this.outputProviderDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private ExecutionGraphDto resolve(ExecutionGraph executionGraph) {
        return this.executionGraphDtoAdapter.adapte(executionGraph);
    }

    private OrchestrableContextStatusDto resolve(OrchestrableContextStatus status) {
        return switch (status) {
            case NEW -> OrchestrableContextStatusDto.NEW;
            case PENDING_START -> OrchestrableContextStatusDto.PENDING_START;
            case RUNNING -> OrchestrableContextStatusDto.RUNNING;
            case PAUSED -> OrchestrableContextStatusDto.PAUSED;
            case CANCELLED -> OrchestrableContextStatusDto.CANCELLED;
            case FAILED -> OrchestrableContextStatusDto.FAILED;
            case SUCCESS -> OrchestrableContextStatusDto.SUCCESS;
        };
    }

    private TechnicalStageRunDto resolve(TechnicalStageRun stageRun) {
        return new TechnicalStageRunDto(
                stageRun.getStageRunId(),
                stageRun.getContextualizedStageId(),
                resolve(stageRun.getContext()),
                resolve(stageRun.getStatus()),
                stageRun.getExecutorId(),
                resolve(stageRun.getStageRunDependencies()),
                stageRun.getStartTime(),
                stageRun.getEndTime()
        );
    }

    private Map<String, StageRunDto<?>> resolve(Map<String, StageRun<?>> stageRunsById) {
        return stageRunsById.entrySet().stream()
                .collect(Collectors.toMap(
                        entry-> entry.getKey(),
                        entry -> this.adapte(entry.getValue())
                ));
    }

    private RunContextDto<?> resolve(RunContext<?> runContext) {
        return switch (runContext.getContextType()) {
            case FLOW_RUN -> new FlowRunContextDto(((FlowRunContext) runContext).getContextId());
            case FUNCTIONAL_STAGE_RUN -> new FunctionalStageRunContextDto(
                    ((FunctionalStageRunContext) runContext).getContextId(),
                    resolve(runContext.getStageRunDependencies())
            );
        };
    }

    private Set<StageRunDependencyDto> resolve(Set<RunDependency> stageRunDependencies) {
        return stageRunDependencies == null ? Set.of() : stageRunDependencies.stream()
                .map(this.stageRunDependencyDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private StageRunStatusDto resolve(TechnicalStageRunStatus technicalStageRunStatus) {
        return switch (technicalStageRunStatus){
            case TO_BE_REQUESTED -> StageRunStatusDto.TO_BE_REQUESTED;
            case REQUESTED -> StageRunStatusDto.REQUESTED;
            case ACKNOWLEDGED -> StageRunStatusDto.ACKNOWLEDGED;
            case RUNNING -> StageRunStatusDto.RUNNING;
            case TO_BE_CANCELLED -> StageRunStatusDto.TO_BE_CANCELLED;
            case CANCELLED -> StageRunStatusDto.CANCELLED;
            case FAILED -> StageRunStatusDto.FAILED;
            case SUCCESS -> StageRunStatusDto.SUCCESS;
        };
    }
}
