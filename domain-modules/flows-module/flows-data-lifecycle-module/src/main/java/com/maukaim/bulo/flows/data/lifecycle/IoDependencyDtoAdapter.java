package com.maukaim.bulo.flows.data.lifecycle;

import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.io.flows.flow.IoDependencyDto;

public interface IoDependencyDtoAdapter {
    IoDependencyDto adapte(IoDependency ioDependency);
}
