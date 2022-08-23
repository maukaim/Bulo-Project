package com.maukaim.bulo.executors.io.in;

import com.maukaim.bulo.commons.io.ExternalEvent;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class NeedStageRunEvent implements ExternalEvent {
    private String globalStageId;
    private String stageRunId;
    private Map<String, Map<String, Set<String>>> ancestorsOutputByInputName; // Map<InputName, Map<AncestorRunId, Set<OutputName>>>
    private Instant instant;


    public NeedStageRunEvent(String globalStageId, String stageRunId, Map<String, Map<String,Set<String>>> ancestorStageRunIdsByInputName, Instant instant) {
        this.globalStageId = globalStageId;
        this.stageRunId = stageRunId;
        this.ancestorsOutputByInputName = ancestorStageRunIdsByInputName;
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

    public Map<String, Map<String, Set<String>>> getAncestorsOutputByInputName() {
        return ancestorsOutputByInputName;
    }

    @Override
    public String toString() {
        return "NeedStageRunEvent{" +
                "globalStageId='" + globalStageId + '\'' +
                ", stageRunId='" + stageRunId + '\'' +
                ", ancestorStageRunIdsByInputName=" + ancestorsOutputByInputName +
                ", instant=" + instant +
                '}';
    }
}
