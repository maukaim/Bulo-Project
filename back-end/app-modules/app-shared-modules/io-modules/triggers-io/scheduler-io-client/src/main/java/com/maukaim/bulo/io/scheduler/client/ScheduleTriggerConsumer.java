package com.maukaim.bulo.io.scheduler.client;

public interface ScheduleTriggerConsumer {
    void consume(CreateScheduleTriggerInstruction triggerConfig);
}
