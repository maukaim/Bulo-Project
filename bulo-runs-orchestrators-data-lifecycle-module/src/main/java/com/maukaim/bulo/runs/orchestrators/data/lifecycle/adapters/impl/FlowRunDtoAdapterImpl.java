package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.impl;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.data.models.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.models.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.models.FlowRunStatus;
import com.maukaim.bulo.runs.orchestrators.data.models.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.FlowRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.StageRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.io.both.model.FlowRunDto;
import com.maukaim.bulo.runs.orchestrators.io.both.model.FlowRunStatusDto;
import com.maukaim.bulo.runs.orchestrators.io.both.model.StageRunDto;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FlowRunDtoAdapterImpl implements FlowRunDtoAdapter {
    private final StageRunDtoAdapter stageRunDtoAdapter;

    public FlowRunDtoAdapterImpl(StageRunDtoAdapter stageRunDtoAdapter) {
        this.stageRunDtoAdapter = stageRunDtoAdapter;
    }

    @Override
    public FlowRunDto adapte(FlowRun flowRun) {
        return new FlowRunDto(
                flowRun.getFlowRunId(),
                flowRun.getFlowId(),
                resolve(flowRun.getExecutionGraph()),
                resolve(flowRun.getStageRunsById()),
                resolve(flowRun.getFlowRunStatus())

        );
    }

    private FlowRunStatusDto resolve(FlowRunStatus flowRunStatus) {
        return switch (flowRunStatus){
            case NEW -> FlowRunStatusDto.NEW;
            case PENDING_START -> FlowRunStatusDto.PENDING_START;
            case RUNNING -> FlowRunStatusDto.RUNNING;
            case PAUSED -> FlowRunStatusDto.PAUSED;
            case CANCELLED -> FlowRunStatusDto.CANCELLED;
            case FAILED -> FlowRunStatusDto.FAILED;
            case SUCCESS -> FlowRunStatusDto.SUCCESS;
        };
    }

    private Map<String, StageRunDto> resolve(Map<String, StageRun> stageRunsById) {
        return stageRunsById.entrySet().stream()
                .collect(Collectors.toMap(
                        entry-> entry.getKey(),
                        entry -> this.stageRunDtoAdapter.adapte(entry.getValue())
                ));
    }

    private Map<FlowStageId, Set<FlowStageId>> resolve(ExecutionGraph<FlowStageId> executionGraph) {
        return executionGraph.getAllStageIds().stream()
                .collect(Collectors.toMap(
                        flowStageId -> flowStageId,
                        flowStageId -> executionGraph.getAncestors(flowStageId)
                ));
    }
}
