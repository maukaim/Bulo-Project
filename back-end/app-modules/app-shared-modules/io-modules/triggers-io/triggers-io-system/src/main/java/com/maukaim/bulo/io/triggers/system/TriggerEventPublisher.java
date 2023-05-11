package com.maukaim.bulo.io.triggers.system;

import com.maukaim.bulo.io.triggers.system.events.BasicTriggerEvent;

public interface TriggerEventPublisher{
    void publish(BasicTriggerEvent event);
}
