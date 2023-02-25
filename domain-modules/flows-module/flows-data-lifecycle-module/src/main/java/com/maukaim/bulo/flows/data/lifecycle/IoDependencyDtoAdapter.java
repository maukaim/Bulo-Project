package com.maukaim.bulo.flows.data.lifecycle;

import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.io.flows.client.model.InputDependencyDto;

public interface IoDependencyDtoAdapter {
    InputDependencyDto adapte(IoDependency ioDependency);
}
