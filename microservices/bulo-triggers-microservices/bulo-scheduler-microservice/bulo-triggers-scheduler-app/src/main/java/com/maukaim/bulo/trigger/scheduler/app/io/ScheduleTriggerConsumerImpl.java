package com.maukaim.bulo.trigger.scheduler.app.io;

import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConfigDto;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConsumer;
import com.maukaim.bulo.triggers.scheduler.data.ScheduleTriggerConfig;
import com.maukaim.bulo.triggers.scheduler.data.lifecycle.ScheduleTriggerConfigAdapter;

public class ScheduleTriggerConsumerImpl implements ScheduleTriggerConsumer {
    private final ScheduleTriggerConfigAdapter configAdapter;
    private final ScheduleTriggerService triggerService;

    public ScheduleTriggerConsumerImpl(ScheduleTriggerConfigAdapter configAdapter,
                                       ScheduleTriggerService triggerService) {
        this.configAdapter = configAdapter;
        this.triggerService = triggerService;
    }

    @Override
    public void consume(ScheduleTriggerConfigDto triggerConfig) {
        ScheduleTriggerConfig config = this.configAdapter.adapte(triggerConfig);
        this.triggerService.setTrigger(config);
    }
}
