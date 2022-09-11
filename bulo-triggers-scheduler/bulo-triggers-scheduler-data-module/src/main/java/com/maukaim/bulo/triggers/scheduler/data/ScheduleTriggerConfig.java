package com.maukaim.bulo.triggers.scheduler.data;

import com.maukaim.bulo.commons.models.TriggerId;

public class ScheduleTriggerConfig {
    private final TriggerId triggerId;
    private final String cronExpression;

    public ScheduleTriggerConfig(TriggerId triggerId,
                                 String cronExpression) {
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
