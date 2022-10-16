package com.maukaim.bulo.runs.orchestrators.app.connectors;

import com.maukaim.bulo.runs.orchestrators.core.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunDependencyDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunCancellationEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunExecutionEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.events.NeedStageRunCancellationEvent;
import com.maukaim.bulo.runs.orchestrators.io.events.NeedStageRunExecutionEvent;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public class StageRunConnectorImpl implements StageRunConnector {
    private final NeedStageRunExecutionEventPublisher needStageRunExecutionEventPublisher;
    private final NeedStageRunCancellationEventPublisher needStageRunCancellationEventPublisher;
    private final StageRunDependencyDtoAdapter stageRunDependencyDtoAdapter;

    public StageRunConnectorImpl(NeedStageRunExecutionEventPublisher needStageRunExecutionEventPublisher, NeedStageRunCancellationEventPublisher needStageRunCancellationEventPublisher, StageRunDependencyDtoAdapter stageRunDependencyDtoAdapter) {
        this.needStageRunExecutionEventPublisher = needStageRunExecutionEventPublisher;
        this.needStageRunCancellationEventPublisher = needStageRunCancellationEventPublisher;
        this.stageRunDependencyDtoAdapter = stageRunDependencyDtoAdapter;
    }


    @Override
    public boolean sendCancel(String stageRunId, String executorId) {
        return this.needStageRunCancellationEventPublisher.publish(
                new NeedStageRunCancellationEvent(stageRunId, executorId, Instant.now())
        );
    }

    @Override
    public boolean sendRun(String stageId, String stageRunId, Set<StageRunDependency> stageRunDependencies) {
        Set<StageRunDependencyDto> stageRunDependencyDtos = resolve(stageRunDependencies);
        return this.needStageRunExecutionEventPublisher.publish(
                new NeedStageRunExecutionEvent(stageId, stageRunId, stageRunDependencyDtos, Instant.now())
        );
    }

    private Set<StageRunDependencyDto> resolve(Set<StageRunDependency> stageRunDependencies) {
        return stageRunDependencies == null ? Set.of() : stageRunDependencies.stream()
                .map(this.stageRunDependencyDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
