package com.maukaim.bulo.serialization.flows.client.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.flows.client.model.InputProviderDto;

import java.util.Set;

public class IoDependencyDtoMixIn {

    @JsonCreator
    public IoDependencyDtoMixIn(@JsonProperty("inputId") String inputId,
                                @JsonProperty("inputProviders") Set<InputProviderDto> inputProviders) {
    }
}
