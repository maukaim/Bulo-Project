package com.maukaim.boule.flows.orchestrator.stage;

import com.maukaim.boule.flows.orchestrator.stage.event.StageEventType;

public interface StageEvent {
    StageEventType getEventType();
    String getStageId();
    String getFlowRunId();
}
