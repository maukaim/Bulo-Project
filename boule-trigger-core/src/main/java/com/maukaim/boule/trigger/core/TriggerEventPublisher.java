package com.maukaim.boule.trigger.core;

import com.maukaim.boule.triggers.api.TriggerEvent;

public interface TriggerEventPublisher{
    void publish(TriggerEvent event);
}
