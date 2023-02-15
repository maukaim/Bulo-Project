package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.executors.core.StageRunEventProcessor;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunDependencyAdapter;
import com.maukaim.bulo.executors.data.runs.StageRunDependency;
import com.maukaim.bulo.io.executors.NeedStageRunEventConsumer;
import com.maukaim.bulo.io.executors.in.RunInstruction;
import com.maukaim.bulo.io.executors.in.model.StageRunDependencyDto;

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
    public void consume(RunInstruction event) {
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
