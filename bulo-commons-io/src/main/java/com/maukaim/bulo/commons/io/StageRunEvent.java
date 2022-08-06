package com.maukaim.bulo.commons.io;

public interface StageRunEvent extends ExternalEvent {
    StageRunEventType getEventType();
    String getStageRunId();
}
