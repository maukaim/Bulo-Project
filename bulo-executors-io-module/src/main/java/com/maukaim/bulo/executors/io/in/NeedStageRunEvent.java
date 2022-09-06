package com.maukaim.bulo.executors.io.in;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.executors.io.in.model.StageRunDependencyDto;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class NeedStageRunEvent implements ExternalEvent {
    private String globalStageId;
    private String stageRunId;
    private Set<StageRunDependencyDto> dependencies;
    private Instant instant;


    public NeedStageRunEvent(String globalStageId, String stageRunId, Set<StageRunDependencyDto> dependencies, Instant instant) {
        this.globalStageId = globalStageId;
        this.stageRunId = stageRunId;
        this.dependencies = dependencies;
        this.instant = instant;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    public String getGlobalStageId() {
        return globalStageId;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    public Set<StageRunDependencyDto> getDependencies() {
        return dependencies;
    }

    @Override
    public String toString() {
        return "NeedStageRunEvent{" +
                "globalStageId='" + globalStageId + '\'' +
                ", stageRunId='" + stageRunId + '\'' +
                ", dependencies=" + dependencies +
                ", instant=" + instant +
                '}';
    }
}
