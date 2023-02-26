package com.maukaim.bulo.serialization.definitions.client.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.data.types.IoTypeDto;

public class StageInputDefinitionDtoMixIn {
    @JsonCreator
    public StageInputDefinitionDtoMixIn(@JsonProperty("ioType") IoTypeDto ioType) {
    }
}
