package com.maukaim.bulo.trigger.scheduler.app.io;

import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.io.scheduler.client.CreateScheduleTriggerInstruction;
import com.maukaim.bulo.io.scheduler.client.ScheduleTriggerConsumer;

public class ScheduleTriggerConsumerImpl implements ScheduleTriggerConsumer {
    private final ScheduleTriggerService triggerService;

    public ScheduleTriggerConsumerImpl(ScheduleTriggerService triggerService) {
        this.triggerService = triggerService;
    }

    @Override
    public void consume(CreateScheduleTriggerInstruction instruction) {
        this.triggerService.setTrigger(instruction.getTriggerId(), instruction.getCronExpression());
    }
}
