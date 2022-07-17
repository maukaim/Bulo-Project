package com.maukaim.bulo.runs.orchestrator.io.in;

import com.maukaim.bulo.commons.events.ExternalEvent;

public interface StageRunEvent extends ExternalEvent {
    StageRunEventType getEventType();

    String getStageRunId();
}
