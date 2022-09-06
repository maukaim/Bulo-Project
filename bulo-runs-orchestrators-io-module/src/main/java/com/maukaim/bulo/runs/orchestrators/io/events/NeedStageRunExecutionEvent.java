package com.maukaim.bulo.runs.orchestrators.io.events;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;

public class NeedStageRunExecutionEvent implements ExternalEvent {
    private String globalStageId;
    private String stageRunId;
    private Set<StageRunDependencyDto> dependencies;
    private Instant instant;

    public NeedStageRunExecutionEvent(String globalStageId, String stageRunId, Set<StageRunDependencyDto> stageRunDependencies, Instant instant) {
        this.globalStageId = globalStageId;
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

    public String getGlobalStageId() {
        return globalStageId;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    @Override
    public String toString() {
        return "NeedStageRunExecutionEvent{" +
                "globalStageId='" + globalStageId + '\'' +
                ", stageRunId='" + stageRunId + '\'' +
                ", dependencies=" + dependencies +
                ", instant=" + instant +
                '}';
    }
}
