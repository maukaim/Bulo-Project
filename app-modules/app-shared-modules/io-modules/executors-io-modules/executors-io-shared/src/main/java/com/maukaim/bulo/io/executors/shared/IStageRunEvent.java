package com.maukaim.bulo.io.executors.shared;

import com.maukaim.bulo.io.shared.ExternalEvent;

public interface IStageRunEvent extends ExternalEvent {
    StageRunEventType getEventType();
    String getStageRunId();
}
