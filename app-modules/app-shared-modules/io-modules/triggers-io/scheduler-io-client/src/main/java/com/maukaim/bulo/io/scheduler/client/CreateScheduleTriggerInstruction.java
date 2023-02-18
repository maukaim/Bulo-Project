package com.maukaim.bulo.io.scheduler.client;

import com.maukaim.bulo.commons.models.TriggerId;

public class CreateScheduleTriggerInstruction {
    private final TriggerId triggerId;
    private final String cronExpression;

    public CreateScheduleTriggerInstruction(TriggerId triggerId,
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
