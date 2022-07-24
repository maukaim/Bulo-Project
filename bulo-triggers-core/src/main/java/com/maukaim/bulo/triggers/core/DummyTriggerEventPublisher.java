package com.maukaim.bulo.triggers.core;

import com.maukaim.bulo.triggers.io.out.BasicTriggerEvent;

import java.time.Instant;

public class DummyTriggerEventPublisher implements TriggerEventPublisher {
    @Override
    public void publish(BasicTriggerEvent event) {
        System.out.printf("PUBLISH %s : %s%n",
                Instant.now(),
                event);
    }
}
