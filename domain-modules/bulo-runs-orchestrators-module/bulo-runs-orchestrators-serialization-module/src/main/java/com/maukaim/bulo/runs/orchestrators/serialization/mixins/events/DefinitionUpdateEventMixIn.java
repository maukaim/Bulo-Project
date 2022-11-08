package com.maukaim.bulo.runs.orchestrators.serialization.mixins.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.runs.orchestrators.io.models.definition.StageDefinitionDto;

import java.time.Instant;

public class DefinitionUpdateEventMixIn {

    @JsonCreator
    public DefinitionUpdateEventMixIn(@JsonProperty("stageDefinition") StageDefinitionDto technicalStageDefinition,
                                      @JsonProperty("eventType") DefinitionEventType eventType,
                                      @JsonProperty("instant") Instant instant){}
}
