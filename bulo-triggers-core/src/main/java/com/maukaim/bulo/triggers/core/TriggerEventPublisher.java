package com.maukaim.bulo.triggers.core;

import com.maukaim.bulo.triggers.io.out.BasicTriggerEvent;

public interface TriggerEventPublisher{
    void publish(BasicTriggerEvent event);
}
