package com.maukaim.bulo.definitions.registry.serialization.mixins.functional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class IoDependencyDtoMixIn {
    @JsonCreator
    public IoDependencyDtoMixIn(@JsonProperty("inputId")  String inputId,
                                @JsonProperty("inputProviders") Set<InputProviderDtoMixIn> inputProviders) {
    }
}
