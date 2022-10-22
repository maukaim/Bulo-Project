package com.maukaim.bulo.triggers.scheduler.data;

import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Set;

public interface TriggerConnector {
    void requestTrigger(String flowId, Set<ContextualizedStageId> contextualizedStageIds);
}
