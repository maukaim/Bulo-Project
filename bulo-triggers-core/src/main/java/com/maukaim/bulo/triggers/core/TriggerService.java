package com.maukaim.bulo.triggers.core;



import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Set;

public interface TriggerService<TC> {

    TC setTrigger(TC triggerConfig);
    boolean removeTrigger(String flowId, Set<FlowStageId> stageIds);
}
