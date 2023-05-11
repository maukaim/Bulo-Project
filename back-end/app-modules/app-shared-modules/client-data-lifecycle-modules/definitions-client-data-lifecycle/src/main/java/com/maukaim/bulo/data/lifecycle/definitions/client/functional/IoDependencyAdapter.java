package com.maukaim.bulo.data.lifecycle.definitions.client.functional;

import com.maukaim.bulo.io.definitions.client.dtos.functional.IoDependencyDto;
import com.maukaim.bulo.definitions.data.definition.functional.IoDependency;

public interface IoDependencyAdapter {
    IoDependency adapte(IoDependencyDto dto);
}
