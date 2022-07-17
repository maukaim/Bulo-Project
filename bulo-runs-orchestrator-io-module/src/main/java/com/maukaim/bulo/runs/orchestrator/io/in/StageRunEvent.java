package com.maukaim.bulo.runs.orchestrator.io.in;

import com.maukaim.bulo.common.models.events.ExternalEvent;

public interface StageRunEvent extends ExternalEvent {
    StageRunEventType getEventType();

    String getStageRunId();
}
