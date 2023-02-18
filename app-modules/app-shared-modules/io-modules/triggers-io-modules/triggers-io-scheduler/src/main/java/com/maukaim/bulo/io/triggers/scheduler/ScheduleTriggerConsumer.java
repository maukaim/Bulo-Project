package com.maukaim.bulo.io.triggers.scheduler;

public interface ScheduleTriggerConsumer {
    void consume(ScheduleTriggerAddInstruction triggerConfig);
}
