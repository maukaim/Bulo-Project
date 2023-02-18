package com.maukaim.bulo.runs.orchestrators.serialization.mixins.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.runs.orchestrators.system.models.definition.DefinitionEventType;
import com.maukaim.bulo.io.runs.orchestrators.system.models.definition.StageDefinitionDto;

import java.time.Instant;

public class DefinitionUpdateEventMixIn {

    @JsonCreator
    public DefinitionUpdateEventMixIn(@JsonProperty("stageDefinition") StageDefinitionDto technicalStageDefinition,
                                      @JsonProperty("eventType") DefinitionEventType eventType,
                                      @JsonProperty("instant") Instant instant){}
}
