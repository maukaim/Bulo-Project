package com.maukaim.bulo.commons.io;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Set;

public interface TriggerEvent extends ExternalEvent {
    String getFlowId();

    Set<ContextStageId> getFlowStageIds();
}
