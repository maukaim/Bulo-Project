package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.ExecutionGraphAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowRunAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.OrchestrableContextStatusDto;

import java.util.Map;
import java.util.stream.Collectors;

public class FlowRunAdapterImpl implements FlowRunAdapter {
    private final StageRunAdapter stageRunAdapter;
    private final ExecutionGraphAdapter executionGraphAdapter;

    public FlowRunAdapterImpl(StageRunAdapter stageRunAdapter,
                              ExecutionGraphAdapter executionGraphAdapter) {
        this.stageRunAdapter = stageRunAdapter;
        this.executionGraphAdapter = executionGraphAdapter;
    }

    @Override
    public FlowRun adapte(FlowRunDto dto) {
        return new FlowRun(
                dto.getContextId(),
                dto.getFlowId(),
                this.executionGraphAdapter.adapte(dto.getExecutionGraph()),
                resolve(dto.getStageRunByIds()),
                resolve(dto.getOrchestrableContextStatus())
        );
    }

    private OrchestrableContextStatus resolve(OrchestrableContextStatusDto flowRunStatus) {
        return switch (flowRunStatus) {
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
                        entry -> this.stageRunAdapter.adapte(entry.getValue())
                ));
    }
}
