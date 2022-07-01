package com.maukaim.bulo.trigger.core;

import java.util.Set;

public interface TriggerService<TC> {

    TC setTrigger(TC triggerConfig);
    boolean removeTrigger(String flowId, Set<String> stageIds);
}
