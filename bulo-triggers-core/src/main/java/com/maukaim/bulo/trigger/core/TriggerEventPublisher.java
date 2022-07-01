package com.maukaim.bulo.trigger.core;

import com.maukaim.bulo.triggers.api.TriggerEvent;

public interface TriggerEventPublisher{
    void publish(TriggerEvent event);
}
