package com.maukaim.bulo.triggers.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class TriggerEvent {
    private final TriggerId triggerId;
    private final Instant instant;

    public TriggerEvent(@JsonProperty("triggerId") TriggerId triggerId,
                        @JsonProperty("instant") Instant instant) {
        this.triggerId = triggerId;
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
