package com.maukaim.boule.triggers.api;

import java.time.Instant;

public class TriggerEvent {
    private final TriggerId triggerId;
    private final Instant instant;

    public TriggerEvent(TriggerId id, Instant instant) {
        this.triggerId = id;
        this.instant = instant;
    }

    public TriggerId getTriggerId() {
        return triggerId;
    }

    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return "TriggerEvent{" +
                "triggerId=" + triggerId +
                ", instant=" + instant +
                '}';
    }
}
