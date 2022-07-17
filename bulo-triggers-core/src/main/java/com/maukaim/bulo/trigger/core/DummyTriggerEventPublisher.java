package com.maukaim.bulo.trigger.core;

import com.maukaim.bulo.triggers.api.BasicTriggerEvent;

import java.time.Instant;

public class DummyTriggerEventPublisher implements TriggerEventPublisher {
    @Override
    public void publish(BasicTriggerEvent event) {
        System.out.printf("PUBLISH %s : %s%n",
                Instant.now(),
                event);
    }
}
