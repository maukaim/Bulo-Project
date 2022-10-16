package com.maukaim.bulo.triggers.io;


import com.maukaim.bulo.triggers.io.events.BasicTriggerEvent;

public interface TriggerEventPublisher{
    void publish(BasicTriggerEvent event);
}
