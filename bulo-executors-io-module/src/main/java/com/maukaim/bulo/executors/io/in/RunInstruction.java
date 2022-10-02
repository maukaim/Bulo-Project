package com.maukaim.bulo.executors.io.in;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.executors.io.in.model.StageRunDependencyDto;

import java.time.Instant;
import java.util.Set;

public class RunInstruction implements ExternalEvent {
    private String stageId;
    private String stageRunId;
    private Set<StageRunDependencyDto> dependencies;
    private Instant instant;


    public RunInstruction(String stageId, String stageRunId, Set<StageRunDependencyDto> dependencies, Instant instant) {
        this.stageId = stageId;
        this.stageRunId = stageRunId;
        this.dependencies = dependencies;
        this.instant = instant;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    public String getStageId() {
        return stageId;
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
                "stageId='" + stageId + '\'' +
                ", stageRunId='" + stageRunId + '\'' +
                ", dependencies=" + dependencies +
                ", instant=" + instant +
                '}';
    }
}
