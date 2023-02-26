package com.maukaim.bulo.serialization.executors.system.mixins.definitions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.data.types.IoTypeDto;

public class StageOutputDefinitionDtoMixIn {
    @JsonCreator
    public StageOutputDefinitionDtoMixIn(@JsonProperty("ioType") IoTypeDto ioType) {
    }
}
