package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.ExecutionGraphDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRunStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowRunStatusDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageDependencyDto;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunDto;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FlowRunDtoAdapterImpl implements FlowRunDtoAdapter {
    private final StageRunDtoAdapter stageRunDtoAdapter;
    private final ExecutionGraphDtoAdapter executionGraphDtoAdapter;

    public FlowRunDtoAdapterImpl(StageRunDtoAdapter stageRunDtoAdapter,
                                 ExecutionGraphDtoAdapter executionGraphDtoAdapter){
        this.stageRunDtoAdapter = stageRunDtoAdapter;
        this.executionGraphDtoAdapter = executionGraphDtoAdapter;
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

    private ExecutionGraphDto resolve(ExecutionGraph executionGraph) {
        return this.executionGraphDtoAdapter.adapte(executionGraph);
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
}
