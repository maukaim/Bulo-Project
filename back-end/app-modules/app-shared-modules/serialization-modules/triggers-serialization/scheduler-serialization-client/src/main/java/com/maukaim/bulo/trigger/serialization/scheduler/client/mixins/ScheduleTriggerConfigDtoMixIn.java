package com.maukaim.bulo.trigger.serialization.scheduler.client.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.TriggerId;

public class ScheduleTriggerConfigDtoMixIn {

    @JsonCreator
    public ScheduleTriggerConfigDtoMixIn(@JsonProperty("triggerId") TriggerId triggerId,
                                         @JsonProperty("cronExpression") String cronExpression){}
}
