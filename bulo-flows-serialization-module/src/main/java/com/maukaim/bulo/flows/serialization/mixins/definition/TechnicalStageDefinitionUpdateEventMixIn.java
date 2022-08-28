package com.maukaim.bulo.flows.serialization.mixins.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.flows.io.definition.TechnicalStageDefinitionDto;

import java.time.Instant;

public class TechnicalStageDefinitionUpdateEventMixIn {
    @JsonCreator
    public TechnicalStageDefinitionUpdateEventMixIn(@JsonProperty("definition") TechnicalStageDefinitionDto definitionDto,
                                                    @JsonProperty("eventType") DefinitionEventType eventType,
                                                    @JsonProperty("instant") Instant instant){}
}
