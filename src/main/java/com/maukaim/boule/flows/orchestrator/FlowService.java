package com.maukaim.boule.flows.orchestrator;

import com.maukaim.boule.triggers.api.TriggerEvent;

public interface FlowService {
    public void startFlow(TriggerEvent event);
}
