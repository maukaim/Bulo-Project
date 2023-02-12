package com.maukaim.bulo.flows.serialization.mixins.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.flows.io.flow.OwnerKeyTypeDto;

public class OwnerKeyDtoMixIn {

    @JsonCreator
    public OwnerKeyDtoMixIn(@JsonProperty("ownerId") String id,
                            @JsonProperty("type") OwnerKeyTypeDto type) {
    }
}
