package com.maukaim.bulo.io.runs.orchestrators.system.events;

import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunDependencyDto;
import com.maukaim.bulo.io.shared.ExternalEvent;

import java.time.Instant;
import java.util.Set;

public class NeedStageRunExecutionEvent implements ExternalEvent {
    private final String stageId;
    private final String stageRunId;
    private final Set<StageRunDependencyDto> dependencies;
    private final Instant instant;

    public NeedStageRunExecutionEvent(String stageId, String stageRunId, Set<StageRunDependencyDto> stageRunDependencies, Instant instant) {
        this.stageId = stageId;
        this.stageRunId = stageRunId;
        this.dependencies = stageRunDependencies;
        this.instant = instant;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    public Set<StageRunDependencyDto> getDependencies() {
        return dependencies;
    }

    public String getStageId() {
        return stageId;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    @Override
    public String toString() {
        return "NeedStageRunExecutionEvent{" +
                "stageId='" + stageId + '\'' +
                ", stageRunId='" + stageRunId + '\'' +
                ", dependencies=" + dependencies +
                ", instant=" + instant +
                '}';
    }
}
