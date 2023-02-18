package com.maukaim.bulo.io.triggers;

import com.maukaim.bulo.io.triggers.events.BasicTriggerEvent;

public interface TriggerEventPublisher{
    void publish(BasicTriggerEvent event);
}
