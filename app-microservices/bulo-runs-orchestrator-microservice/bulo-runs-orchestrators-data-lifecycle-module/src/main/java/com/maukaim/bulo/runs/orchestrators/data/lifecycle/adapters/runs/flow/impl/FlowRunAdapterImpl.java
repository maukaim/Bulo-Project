package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.ExecutionGraphAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowRunAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRunStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowRunStatusDto;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunDto;

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
                dto.getFlowRunId(),
                dto.getFlowId(),
                this.executionGraphAdapter.adapte(dto.getExecutionGraph()),
                resolve(dto.getStageRunByIds()),
                resolve(dto.getFlowRunStatus())
        );
    }

    private FlowRunStatus resolve(FlowRunStatusDto flowRunStatus) {
        return switch (flowRunStatus) {
            case NEW -> FlowRunStatus.NEW;
            case PENDING_START -> FlowRunStatus.PENDING_START;
            case RUNNING -> FlowRunStatus.RUNNING;
            case PAUSED -> FlowRunStatus.PAUSED;
            case CANCELLED -> FlowRunStatus.CANCELLED;
            case FAILED -> FlowRunStatus.FAILED;
            case SUCCESS -> FlowRunStatus.SUCCESS;
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
