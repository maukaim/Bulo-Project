package com.maukaim.bulo.trigger.scheduler;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.triggers.api.TriggerId;

public class ScheduleTriggerConfig {
    private final TriggerId triggerId;
    private final String cronExpression;

    @JsonCreator
    public ScheduleTriggerConfig(@JsonProperty("triggerId") TriggerId triggerId,
                                 @JsonProperty("cronExpression") String cronExpression) {
        this.cronExpression = cronExpression;
        this.triggerId = triggerId;
    }


    public String getCronExpression() {
        return cronExpression;
    }

    public TriggerId getTriggerId() {
        return this.triggerId;
    }

}
