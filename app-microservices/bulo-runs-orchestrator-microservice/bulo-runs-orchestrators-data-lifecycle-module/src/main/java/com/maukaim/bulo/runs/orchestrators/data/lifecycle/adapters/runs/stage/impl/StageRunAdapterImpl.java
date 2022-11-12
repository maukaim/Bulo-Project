package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.impl;

import com.maukaim.bulo.commons.io.instructions.models.functional.OutputProviderDto;
import com.maukaim.bulo.runs.orchestrators.data.definition.OutputProvider;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.definitions.OutputProviderAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.ExecutionGraphAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunDependencyAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.*;
import com.maukaim.bulo.runs.orchestrators.io.models.FunctionalStageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunStatusDto;
import com.maukaim.bulo.runs.orchestrators.io.models.TechnicalStageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.OrchestrableContextStatusDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.ContextDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.FlowContextDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.FunctionalStageContextDto;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StageRunAdapterImpl implements StageRunAdapter {
    private final ExecutionGraphAdapter executionGraphAdapter;
    private final StageRunDependencyAdapter stageRunDependencyAdapter;
    private final OutputProviderAdapter outputProviderAdapter;

    public StageRunAdapterImpl(ExecutionGraphAdapter executionGraphAdapter,
                               StageRunDependencyAdapter stageRunDependencyAdapter,
                               OutputProviderAdapter outputProviderAdapter) {
        this.executionGraphAdapter = executionGraphAdapter;
        this.stageRunDependencyAdapter = stageRunDependencyAdapter;
        this.outputProviderAdapter = outputProviderAdapter;
    }

    @Override
    public StageRun adapte(StageRunDto dto) {
        return switch (dto.getStageType()) {
            case TECHNICAL -> resolve((FunctionalStageRunDto) dto);
            case FUNCTIONAL -> resolve((TechnicalStageRunDto) dto);
        };
    }

    private TechnicalStageRun resolve(TechnicalStageRunDto dto) {
        return new TechnicalStageRun(
                dto.getStageRunId(),
                dto.getContextStageId(),
                resolve(dto.getContext()),
                resolve(dto.getStatus()),
                dto.getExecutorId(),
                resolve(dto.getDependencies()),
                dto.getStartTime(),
                dto.getEndTime()
        );
    }

    private FunctionalStageRun resolve(FunctionalStageRunDto dto) {
        return new FunctionalStageRun(
                dto.getStageRunId(),
                dto.getContextStageId(),
                resolve(dto.getContext()),
                resolve(dto.getStatus()),
                resolve(dto.getDependencies()),
                dto.getStartTime(),
                dto.getEndTime(),
                resolveOutputProviders(dto.getOutputProviders()),
                resolve(dto.getExecutionGraph()),
                resolve(dto.getStageRunByIds())
        );
    }

    private Set<OutputProvider> resolveOutputProviders(Set<OutputProviderDto> outputProviderDtos){
        return outputProviderDtos == null ? Set.of() : outputProviderDtos.stream()
                .map(this.outputProviderAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private ExecutionGraph resolve(ExecutionGraphDto dto) {
        return this.executionGraphAdapter.adapte(dto);
    }

    private OrchestrableContextStatus resolve(OrchestrableContextStatusDto statusDto) {
        return switch (statusDto) {
            case NEW -> OrchestrableContextStatus.NEW;
            case PENDING_START -> OrchestrableContextStatus.PENDING_START;
            case RUNNING -> OrchestrableContextStatus.RUNNING;
            case PAUSED -> OrchestrableContextStatus.PAUSED;
            case CANCELLED -> OrchestrableContextStatus.CANCELLED;
            case FAILED -> OrchestrableContextStatus.FAILED;
            case SUCCESS -> OrchestrableContextStatus.SUCCESS;
        };
    }

    private Map<String, StageRun> resolve(Map<String, StageRunDto> stageRunDtoByIds) {
        return stageRunDtoByIds.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> this.adapte(entry.getValue())
                ));
    }

    private RunContext<?> resolve(ContextDto context) {
        return switch (context.getContextType()) {
            case FLOW_RUN -> new FlowRunContext(((FlowContextDto) context).getContextId());
            case FUNCTIONAL_STAGE_RUN ->
                    new FunctionalStageRunContext(
                            ((FunctionalStageContextDto) context).getContextId(),
                            resolve(context.getStageRunDependencies()));
        };
    }

    private Set<RunDependency> resolve(Set<StageRunDependencyDto> dependencies) {
        return dependencies == null ? null : dependencies.stream()
                .map(this.stageRunDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private TechnicalStageRunStatus resolve(StageRunStatusDto stageRunStatusDto) {
        return switch (stageRunStatusDto) {
            case TO_BE_REQUESTED -> TechnicalStageRunStatus.TO_BE_REQUESTED;
            case REQUESTED -> TechnicalStageRunStatus.REQUESTED;
            case ACKNOWLEDGED -> TechnicalStageRunStatus.ACKNOWLEDGED;
            case RUNNING -> TechnicalStageRunStatus.RUNNING;
            case TO_BE_CANCELLED -> TechnicalStageRunStatus.TO_BE_CANCELLED;
            case CANCELLED -> TechnicalStageRunStatus.CANCELLED;
            case FAILED -> TechnicalStageRunStatus.FAILED;
            case SUCCESS -> TechnicalStageRunStatus.SUCCESS;
        };
    }
}
