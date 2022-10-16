package com.maukaim.bulo.flows.data.lifecycle;

import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.flows.data.models.flow.OwnerKey;
import com.maukaim.bulo.flows.io.flow.IoDependencyDto;
import com.maukaim.bulo.flows.io.flow.OwnerKeyDto;

public interface IoDependencyAdapter {
    IoDependency adapte(IoDependencyDto dto);
}
