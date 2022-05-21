package com.maukaim.boule.trigger.core;

public interface TriggerService<TC> {

    TC setTrigger(TC triggerConfig);
    boolean removeTrigger(String flowId, String stageId);
}
