package com.maukaim.bulo.definitions.registry.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StageInputDefinitionMixIn {
    @JsonCreator
    public StageInputDefinitionMixIn(@JsonProperty("canBeMultiple") Boolean acceptMultiple, @JsonProperty("isRequired") Boolean isRequired, @JsonProperty("type") Class type) {
    }
}
