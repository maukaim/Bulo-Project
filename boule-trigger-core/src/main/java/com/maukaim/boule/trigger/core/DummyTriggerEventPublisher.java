package com.maukaim.boule.trigger.core;

import com.maukaim.boule.triggers.api.TriggerEvent;

import java.time.Instant;

public class DummyTriggerEventPublisher implements TriggerEventPublisher {
    @Override
    public void publish(TriggerEvent event) {
        System.out.printf("PUBLISH %s : %s%n",
                Instant.now(),
                event);
    }
}
