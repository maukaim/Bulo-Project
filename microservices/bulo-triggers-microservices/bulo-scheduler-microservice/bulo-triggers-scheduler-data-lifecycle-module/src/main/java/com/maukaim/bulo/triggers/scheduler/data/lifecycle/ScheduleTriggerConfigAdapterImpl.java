package com.maukaim.bulo.triggers.scheduler.data.lifecycle;

import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConfigDto;
import com.maukaim.bulo.triggers.scheduler.data.ScheduleTriggerConfig;

public class ScheduleTriggerConfigAdapterImpl implements ScheduleTriggerConfigAdapter {
    @Override
    public ScheduleTriggerConfig adapte(ScheduleTriggerConfigDto dto) {
        return new ScheduleTriggerConfig(dto.getTriggerId(), dto.getCronExpression());
    }
}
