package com.maukaim.bulo.trigger.scheduler.app.io;

import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.io.triggers.scheduler.ScheduleTriggerAddInstruction;
import com.maukaim.bulo.io.triggers.scheduler.ScheduleTriggerConsumer;

public class ScheduleTriggerConsumerImpl implements ScheduleTriggerConsumer {
    private final ScheduleTriggerService triggerService;

    public ScheduleTriggerConsumerImpl(ScheduleTriggerService triggerService) {
        this.triggerService = triggerService;
    }

    @Override
    public void consume(ScheduleTriggerAddInstruction instruction) {
        this.triggerService.setTrigger(instruction.getTriggerId(), instruction.getCronExpression());
    }
}
