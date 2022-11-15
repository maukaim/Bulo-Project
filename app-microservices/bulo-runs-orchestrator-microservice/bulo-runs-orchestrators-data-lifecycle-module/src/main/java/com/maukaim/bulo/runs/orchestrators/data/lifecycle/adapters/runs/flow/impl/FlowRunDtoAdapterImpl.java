package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.ExecutionGraphDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FailureGraphDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FailureGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.OrchestrableContextStatus;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FailureGraphDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.OrchestrableContextStatusDto;

import java.util.Map;
import java.util.stream.Collectors;

public class FlowRunDtoAdapterImpl implements FlowRunDtoAdapter {
    private final StageRunDtoAdapter stageRunDtoAdapter;
    private final ExecutionGraphDtoAdapter executionGraphDtoAdapter;
    private final FailureGraphDtoAdapter failureGraphDtoAdapter;

    public FlowRunDtoAdapterImpl(StageRunDtoAdapter stageRunDtoAdapter,
                                 ExecutionGraphDtoAdapter executionGraphDtoAdapter,
                                 FailureGraphDtoAdapter failureGraphDtoAdapter){
        this.stageRunDtoAdapter = stageRunDtoAdapter;
        this.executionGraphDtoAdapter = executionGraphDtoAdapter;
        this.failureGraphDtoAdapter = failureGraphDtoAdapter;
    }

    @Override
    public FlowRunDto adapte(FlowRun flowRun) {
        return new FlowRunDto(
                flowRun.getContextId(),
                flowRun.getFlowId(),
                resolve(flowRun.getExecutionGraph()),
                resolve(flowRun.getFailureGraph()),
                resolve(flowRun.getStageRunsById()),
                resolve(flowRun.getStatus())

        );
    }

    private FailureGraphDto resolve(FailureGraph failureGraph) {
        return this.failureGraphDtoAdapter.adapte(failureGraph);
    }

    private ExecutionGraphDto resolve(ExecutionGraph executionGraph) {
        return this.executionGraphDtoAdapter.adapte(executionGraph);
    }

    private OrchestrableContextStatusDto resolve(OrchestrableContextStatus orchestrableContextStatus) {
        return switch (orchestrableContextStatus){
            case NEW -> OrchestrableContextStatusDto.NEW;
            case PENDING_START -> OrchestrableContextStatusDto.PENDING_START;
            case RUNNING -> OrchestrableContextStatusDto.RUNNING;
            case PAUSED -> OrchestrableContextStatusDto.PAUSED;
            case CANCELLED -> OrchestrableContextStatusDto.CANCELLED;
            case FAILED -> OrchestrableContextStatusDto.FAILED;
            case SUCCESS -> OrchestrableContextStatusDto.SUCCESS;
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
