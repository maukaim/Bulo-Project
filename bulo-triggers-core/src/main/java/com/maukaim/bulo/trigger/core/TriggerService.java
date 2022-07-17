package com.maukaim.bulo.trigger.core;


import com.maukaim.bulo.commons.core.models.FlowStageId;

import java.util.Set;

public interface TriggerService<TC> {

    TC setTrigger(TC triggerConfig);
    boolean removeTrigger(String flowId, Set<FlowStageId> stageIds);
}
