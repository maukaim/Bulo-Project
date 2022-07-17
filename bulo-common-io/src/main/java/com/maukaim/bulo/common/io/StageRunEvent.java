package com.maukaim.bulo.common.io;

public interface StageRunEvent extends ExternalEvent {
    StageRunEventType getEventType();
    String getStageRunId();
}
