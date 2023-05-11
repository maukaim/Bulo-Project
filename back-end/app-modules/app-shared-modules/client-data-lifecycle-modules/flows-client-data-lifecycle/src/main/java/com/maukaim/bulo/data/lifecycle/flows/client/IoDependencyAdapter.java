package com.maukaim.bulo.data.lifecycle.flows.client;

import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.io.flows.client.model.InputDependencyDto;

public interface IoDependencyAdapter {
    IoDependency adapte(InputDependencyDto dto);
}
