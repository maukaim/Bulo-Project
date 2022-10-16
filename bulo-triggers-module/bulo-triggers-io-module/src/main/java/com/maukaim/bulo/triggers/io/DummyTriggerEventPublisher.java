package com.maukaim.bulo.triggers.io;


import com.maukaim.bulo.triggers.io.events.BasicTriggerEvent;

import java.time.Instant;

public class DummyTriggerEventPublisher implements TriggerEventPublisher {
    @Override
    public void publish(BasicTriggerEvent event) {
        System.out.printf("PUBLISH %s : %s%n",
                Instant.now(),
                event);
    }
}
