package com.maukaim.bulo.flows.data.lifecycle;

import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.io.flows.client.model.IoDependencyDto;

public interface IoDependencyAdapter {
    IoDependency adapte(IoDependencyDto dto);
}
