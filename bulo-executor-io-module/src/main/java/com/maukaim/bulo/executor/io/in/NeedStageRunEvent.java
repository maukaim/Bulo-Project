package com.maukaim.bulo.executor.io.in;

import com.maukaim.bulo.commons.io.ExternalEvent;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class NeedStageRunEvent implements ExternalEvent {
    private String globalStageId;
    private String stageRunId;
    private Map<String, Set<String>> ancestorStageRunIdsByInputName;
    private Instant instant;


    public NeedStageRunEvent(String globalStageId, String stageRunId, Map<String, Set<String>> ancestorStageRunIdsByInputName, Instant instant) {
        this.globalStageId = globalStageId;
        this.stageRunId = stageRunId;
        this.ancestorStageRunIdsByInputName = ancestorStageRunIdsByInputName;
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

    public Map<String, Set<String>> getAncestorStageRunIdsByInputName() {
        return ancestorStageRunIdsByInputName;
    }

    @Override
    public String toString() {
        return "NeedStageRunEvent{" +
                "globalStageId='" + globalStageId + '\'' +
                ", stageRunId='" + stageRunId + '\'' +
                ", ancestorStageRunIdsByInputName=" + ancestorStageRunIdsByInputName +
                ", instant=" + instant +
                '}';
    }
}
