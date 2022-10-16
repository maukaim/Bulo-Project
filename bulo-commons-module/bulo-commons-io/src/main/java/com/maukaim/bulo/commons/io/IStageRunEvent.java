package com.maukaim.bulo.commons.io;

public interface IStageRunEvent extends ExternalEvent {
    StageRunEventType getEventType();
    String getStageRunId();
}
