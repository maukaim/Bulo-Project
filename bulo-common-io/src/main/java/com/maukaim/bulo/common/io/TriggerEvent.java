package com.maukaim.bulo.common.io;

import com.maukaim.bulo.commons.core.models.FlowStageId;

import java.util.Set;

public interface TriggerEvent extends ExternalEvent{
    String getFlowId();
    Set<FlowStageId> getFlowStageIds();
}
