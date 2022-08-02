package com.maukaim.bulo.definitions.registry.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StageOutputDefinitionMixIn {
    @JsonCreator
    public StageOutputDefinitionMixIn(@JsonProperty("canBeMultiple") Boolean acceptMultiple, @JsonProperty("type") String typeId) {
    }
}
