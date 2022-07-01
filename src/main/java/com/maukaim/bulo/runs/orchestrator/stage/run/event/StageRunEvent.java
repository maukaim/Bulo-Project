package com.maukaim.bulo.runs.orchestrator.stage.run.event;

public interface StageRunEvent {
    StageRunEventType getEventType();
    String getStageRunId();
}
