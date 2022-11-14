package com.maukaim.bulo.runs.orchestrators.data.flow;

import com.maukaim.bulo.commons.models.ContextStageId;

public class FailureAlternativeRoute {
    private final ContextStageId from;
    private final ContextStageId to;
    private final Integer maxUsage;

    public FailureAlternativeRoute(ContextStageId from, ContextStageId to, Integer maxUsage) {
        this.from = from;
        this.to = to;
        this.maxUsage = maxUsage;
    }

    public ContextStageId getFrom() {
        return from;
    }

    public ContextStageId getTo() {
        return to;
    }

    public Integer getMaxUsage() {
        return maxUsage;
    }

    @Override
    public String toString() {
        return "FailureAlternativeRoute{" +
                "from=" + from +
                ", to=" + to +
                ", maxUsage=" + maxUsage +
                '}';
    }
}
