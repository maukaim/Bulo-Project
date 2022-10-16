package com.maukaim.bulo.trigger.scheduler.io;

public interface ScheduleTriggerConsumer {
    void consume(ScheduleTriggerAddInstruction triggerConfig);
}
