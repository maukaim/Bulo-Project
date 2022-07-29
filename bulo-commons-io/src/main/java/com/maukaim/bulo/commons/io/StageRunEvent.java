package com.maukaim.bulo.commons.io;

import com.maukaim.bulo.commons.models.StageRunEventType;

public interface StageRunEvent extends ExternalEvent {
    StageRunEventType getEventType();
    String getStageRunId();
}
