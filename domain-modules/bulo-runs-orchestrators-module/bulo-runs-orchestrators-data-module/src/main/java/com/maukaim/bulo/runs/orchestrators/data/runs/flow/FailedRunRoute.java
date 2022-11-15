package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import com.maukaim.bulo.commons.models.ContextStageId;

public class FailedRunRoute {
    private final ContextStageId destination;
    private final Integer remainingUsage;

    public FailedRunRoute(ContextStageId destination, Integer remainingUsage) {
        this.destination = destination;
        this.remainingUsage = remainingUsage;
    }

    public ContextStageId getDestination() {
        return destination;
    }

    public Integer getRemainingUsage() {
        return remainingUsage;
    }

    @Override
    public String toString() {
        return "FailedRunRoute{" +
                "destination=" + destination +
                ", remainingUsage=" + remainingUsage +
                '}';
    }
}
