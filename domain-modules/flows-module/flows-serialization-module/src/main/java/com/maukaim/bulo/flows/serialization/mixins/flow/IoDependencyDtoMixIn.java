package com.maukaim.bulo.flows.serialization.mixins.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.flows.flow.InputProviderDto;

import java.util.Set;

public class IoDependencyDtoMixIn {

    @JsonCreator
    public IoDependencyDtoMixIn(@JsonProperty("inputId") String inputId,
                                @JsonProperty("inputProviders") Set<InputProviderDto> inputProviders) {
    }
}
