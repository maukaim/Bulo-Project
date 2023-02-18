package com.maukaim.bulo.io.triggers;

import com.maukaim.bulo.io.triggers.events.BasicTriggerEvent;

import java.time.Instant;

public class DummyTriggerEventPublisher implements TriggerEventPublisher {
    @Override
    public void publish(BasicTriggerEvent event) {
        System.out.printf("PUBLISH %s : %s%n",
                Instant.now(),
                event);
    }
}
