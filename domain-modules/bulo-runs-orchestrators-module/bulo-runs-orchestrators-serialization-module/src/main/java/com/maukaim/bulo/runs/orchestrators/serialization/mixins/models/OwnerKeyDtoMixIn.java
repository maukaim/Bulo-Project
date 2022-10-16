package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.OwnerKeyTypeDto;

public class OwnerKeyDtoMixIn {
    @JsonCreator
    public OwnerKeyDtoMixIn(@JsonProperty("ownerId") String ownerId,
                            @JsonProperty("type") OwnerKeyTypeDto type) {
    }
}
