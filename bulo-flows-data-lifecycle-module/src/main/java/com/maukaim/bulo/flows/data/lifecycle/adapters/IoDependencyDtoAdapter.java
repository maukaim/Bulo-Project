package com.maukaim.bulo.flows.data.lifecycle.adapters;

import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.flows.io.flow.IoDependencyDto;

public interface IoDependencyDtoAdapter {
    IoDependencyDto adapte(IoDependency ioDependency);
}
