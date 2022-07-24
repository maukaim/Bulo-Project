package com.maukaim.bulo.trigger.scheduler.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.triggers.io.TriggerId;

public class ScheduleTriggerConfigMixIn {

    @JsonCreator
    public ScheduleTriggerConfigMixIn(@JsonProperty("triggerId") TriggerId triggerId,
                                      @JsonProperty("cronExpression") String cronExpression){}
}
