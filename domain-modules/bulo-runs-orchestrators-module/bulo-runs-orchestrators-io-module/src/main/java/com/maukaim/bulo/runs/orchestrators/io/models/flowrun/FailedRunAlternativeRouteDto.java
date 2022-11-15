package com.maukaim.bulo.runs.orchestrators.io.models.flowrun;

import com.maukaim.bulo.commons.models.ContextStageId;

public class FailedRunAlternativeRouteDto {
    private final ContextStageId origin;
    private final ContextStageId destination;
    private final Integer remainingUsage;

    public FailedRunAlternativeRouteDto(ContextStageId origin, ContextStageId destination, Integer remainingUsage) {
        this.origin = origin;
        this.destination = destination;
        this.remainingUsage = remainingUsage;
    }

    public ContextStageId getOrigin() {
        return origin;
    }

    public ContextStageId getDestination() {
        return destination;
    }

    public Integer getRemainingUsage() {
        return remainingUsage;
    }

    @Override
    public String toString() {
        return "FailedRunAlternativeRouteDto{" +
                "origin=" + origin +
                ", destination=" + destination +
                ", remainingUsage=" + remainingUsage +
                '}';
    }
}
