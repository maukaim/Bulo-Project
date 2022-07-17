package com.maukaim.bulo.trigger.core;

import com.maukaim.bulo.triggers.api.BasicTriggerEvent;

public interface TriggerEventPublisher{
    void publish(BasicTriggerEvent event);
}
