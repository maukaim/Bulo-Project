package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.executors.core.StageRunEventProcessor;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunDependencyAdapter;
import com.maukaim.bulo.executors.data.runs.StageRunDependency;
import com.maukaim.bulo.executors.data.lifecycle.NeedStageRunEventConsumer;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunExecutionEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class NeedStageRunEventConsumerImpl implements NeedStageRunEventConsumer {
    private final StageRunEventProcessor stageRunEventProcessor;
    private final StageRunDependencyAdapter stageRunDependencyAdapter;

    public NeedStageRunEventConsumerImpl(StageRunEventProcessor stageRunEventProcessor,
                                         StageRunDependencyAdapter stageRunDependencyAdapter) {
        this.stageRunEventProcessor = stageRunEventProcessor;
        this.stageRunDependencyAdapter = stageRunDependencyAdapter;
    }

    @Override
    public void consume(NeedStageRunExecutionEvent event) {
        System.out.println("Consume event: " + event);
        Set<StageRunDependency> stageRunDependencies = resolve(event.getDependencies());
        this.stageRunEventProcessor.onRunRequest(event.getStageId(),
                event.getStageRunId(), stageRunDependencies);
    }

    private Set<StageRunDependency> resolve(Set<StageRunDependencyDto> dependencies) {
        return dependencies == null ? null : dependencies.stream()
                .map(this.stageRunDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
