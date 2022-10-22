package com.maukaim.bulo.flows.serialization.mixins.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.flows.io.definition.stageDefinitionDto;

import java.time.Instant;

public class StageDefinitionUpdateEventMixIn {
    @JsonCreator
    public StageDefinitionUpdateEventMixIn(@JsonProperty("stageDefinition") stageDefinitionDto definitionDto,
                                           @JsonProperty("eventType") DefinitionEventType eventType,
                                           @JsonProperty("instant") Instant instant){}
}
