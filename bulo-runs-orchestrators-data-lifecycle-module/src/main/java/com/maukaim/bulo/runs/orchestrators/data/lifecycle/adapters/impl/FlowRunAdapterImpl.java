package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.impl;

import com.maukaim.bulo.runs.orchestrators.data.models.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.models.FlowRunStatus;
import com.maukaim.bulo.runs.orchestrators.data.models.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.models.UnmodifiableAcyclicExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.FlowRunAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.StageRunAdapter;
import com.maukaim.bulo.runs.orchestrators.io.both.model.FlowRunDto;
import com.maukaim.bulo.runs.orchestrators.io.both.model.FlowRunStatusDto;
import com.maukaim.bulo.runs.orchestrators.io.both.model.StageRunDto;

import java.util.Map;
import java.util.stream.Collectors;

public class FlowRunAdapterImpl implements FlowRunAdapter {
    private final StageRunAdapter stageRunAdapter;

    public FlowRunAdapterImpl(StageRunAdapter stageRunAdapter) {
        this.stageRunAdapter = stageRunAdapter;
    }

    @Override
    public FlowRun adapte(FlowRunDto dto) {
        return new FlowRun(
                dto.getFlowRunId(),
                dto.getFlowId(),
                new UnmodifiableAcyclicExecutionGraph(dto.getStageToAncestors()),
                resolve(dto.getStageRunByIds()),
                resolve(dto.getFlowRunStatus())
        );
    }

    private FlowRunStatus resolve(FlowRunStatusDto flowRunStatus) {
        return switch (flowRunStatus){
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
                        entry-> entry.getKey(),
                        entry -> this.stageRunAdapter.adapte(entry.getValue())
                        ));
    }
}
