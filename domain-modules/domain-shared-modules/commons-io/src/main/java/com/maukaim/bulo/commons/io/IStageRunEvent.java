package com.maukaim.bulo.commons.io;

import com.maukaim.bulo.io.shared.ExternalEvent;

public interface IStageRunEvent extends ExternalEvent {
    StageRunEventType getEventType();
    String getStageRunId();
}
