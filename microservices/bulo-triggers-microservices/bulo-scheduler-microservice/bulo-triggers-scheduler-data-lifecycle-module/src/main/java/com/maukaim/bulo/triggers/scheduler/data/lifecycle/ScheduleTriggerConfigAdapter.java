package com.maukaim.bulo.triggers.scheduler.data.lifecycle;

import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConfigDto;
import com.maukaim.bulo.triggers.scheduler.data.ScheduleTriggerConfig;

public interface ScheduleTriggerConfigAdapter {
    ScheduleTriggerConfig adapte(ScheduleTriggerConfigDto dto);
}
