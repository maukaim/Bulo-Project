package com.maukaim.bulo.flows.serialization.mixins.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.flows.io.flow.InputProviderDto;
import com.maukaim.bulo.flows.io.flow.OwnerKeyTypeDto;

import java.util.Set;

public class IoDependencyDtoMixIn {

    @JsonCreator
    public IoDependencyDtoMixIn(@JsonProperty("inputId") String inputId,
                                @JsonProperty("inputProviders") Set<InputProviderDto> inputProviders) {
    }
}
