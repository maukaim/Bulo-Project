package com.maukaim.bulo.runs.orchestrators.io.out;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.runs.orchestrators.io.out.model.IoMapping;

import java.time.Instant;

public class NeedStageRunExecutionEvent implements ExternalEvent {
    private String globalStageId;
    private String stageRunId;
    private IoMapping ioMapping; // Map<InputName, Map<AncestorRunId, Set<OutputName>>>
    private Instant instant;


    public NeedStageRunExecutionEvent(String globalStageId, String stageRunId, IoMapping ancestorStageRunIdsByInputName, Instant instant) {
        this.globalStageId = globalStageId;
        this.stageRunId = stageRunId;
        this.ioMapping = ancestorStageRunIdsByInputName;
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

    public IoMapping getIoMapping() {
        return ioMapping;
    }

    @Override
    public String toString() {
        return "NeedStageRunEvent{" +
                "globalStageId='" + globalStageId + '\'' +
                ", stageRunId='" + stageRunId + '\'' +
                ", ancestorStageRunIdsByInputName=" + ioMapping +
                ", instant=" + instant +
                '}';
    }
}
