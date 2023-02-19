package com.maukaim.bulo.runs.orchestrators.app.connectors;

import com.maukaim.bulo.io.executors.system.AcknowledgeStageRunEvent;
import com.maukaim.bulo.io.executors.system.RunCancelledStageRunEvent;
import com.maukaim.bulo.io.executors.system.RunFailedStageRunEvent;
import com.maukaim.bulo.io.executors.system.RunSuccessfulStageRunEvent;
import com.maukaim.bulo.io.executors.system.StartRunStageRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunCancellationEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunExecutionEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunDependencyDto;
import com.maukaim.bulo.runs.orchestrators.core.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.NeedStageRunCancellationEventPublisher;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.NeedStageRunExecutionEventPublisher;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.StageRunEventConsumer;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage.StageRunDependencyDtoAdapter;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public class StageRunConnectorImpl implements StageRunConnector {
    private final NeedStageRunExecutionEventPublisher needStageRunExecutionEventPublisher;
    private final NeedStageRunCancellationEventPublisher needStageRunCancellationEventPublisher;
    private final StageRunDependencyDtoAdapter stageRunDependencyDtoAdapter;
    private StageRunEventConsumer stageRunEventConsumer;

    public StageRunConnectorImpl(NeedStageRunExecutionEventPublisher needStageRunExecutionEventPublisher,
                                 NeedStageRunCancellationEventPublisher needStageRunCancellationEventPublisher,
                                 StageRunDependencyDtoAdapter stageRunDependencyDtoAdapter) {
        this.needStageRunExecutionEventPublisher = needStageRunExecutionEventPublisher;
        this.needStageRunCancellationEventPublisher = needStageRunCancellationEventPublisher;
        this.stageRunDependencyDtoAdapter = stageRunDependencyDtoAdapter;
    }

    public void setConsumer(StageRunEventConsumer consumer) {
        this.stageRunEventConsumer = consumer;
    }

    @Override
    public boolean requestCancel(String stageRunId, String executorId) {
        return this.needStageRunCancellationEventPublisher.publish(
                new NeedStageRunCancellationEvent(stageRunId, executorId, Instant.now())
        );
    }

    @Override
    public boolean requestRun(String stageId, String stageRunId, Set<RunDependency> stageRunDependencies) {
        Set<StageRunDependencyDto> stageRunDependencyDtos = resolve(stageRunDependencies);
        return this.needStageRunExecutionEventPublisher.publish(
                new NeedStageRunExecutionEvent(stageId, stageRunId, stageRunDependencyDtos, Instant.now())
        );
    }

    @Override
    public void propagateFunctionalStageRunAcknowleged(String stageRunId) {
        String executorId = "SELF_APP";
        this.stageRunEventConsumer.onStageRunEvent(new AcknowledgeStageRunEvent(executorId, stageRunId, Instant.now()));
    }

    @Override
    public void propagateFunctionalStageRunStarted(String stageRunId) {
        this.stageRunEventConsumer.onStageRunEvent(new StartRunStageRunEvent(stageRunId, Instant.now()));
    }

    @Override
    public void propagateFunctionalStageRunCancelled(String stageRunId) {
        this.stageRunEventConsumer.onStageRunEvent(new RunCancelledStageRunEvent(stageRunId, Instant.now()));
    }

    @Override
    public void propagateFunctionalStageRunFailed(String stageRunId) {
        this.stageRunEventConsumer.onStageRunEvent(new RunFailedStageRunEvent(stageRunId, Instant.now()));
    }

    @Override
    public void propagateFunctionalStageRunSuccesful(String stageRunId) {
        this.stageRunEventConsumer.onStageRunEvent(new RunSuccessfulStageRunEvent(stageRunId, Instant.now()));
    }

    private Set<StageRunDependencyDto> resolve(Set<RunDependency> stageRunDependencies) {
        return stageRunDependencies == null ? Set.of() : stageRunDependencies.stream()
                .map(this.stageRunDependencyDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
