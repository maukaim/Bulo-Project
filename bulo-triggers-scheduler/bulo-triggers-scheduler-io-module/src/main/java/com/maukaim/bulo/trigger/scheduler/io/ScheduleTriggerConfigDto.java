package com.maukaim.bulo.trigger.scheduler.io;

import com.maukaim.bulo.commons.models.TriggerId;

public class ScheduleTriggerConfigDto {
    private final TriggerId triggerId;
    private final String cronExpression;

    public ScheduleTriggerConfigDto(TriggerId triggerId,
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
