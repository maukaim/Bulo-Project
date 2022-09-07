package com.maukaim.bulo.executors.serialization.mixins.definitions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StageOutputDefinitionDtoMixIn {
    @JsonCreator
    public StageOutputDefinitionDtoMixIn(
            @JsonProperty("acceptMultiple") Boolean acceptMultiple,
            @JsonProperty("typeId") String typeId) {
    }
}
