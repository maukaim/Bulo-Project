package com.maukaim.bulo.definitions.data.lifecycle.functional;

import com.maukaim.bulo.commons.io.instructions.models.functional.IoDependencyDto;
import com.maukaim.bulo.definitions.data.definition.functional.IoDependency;

public interface IoDependencyDtoAdapter {
    IoDependencyDto adapte(IoDependency ioDependency);
}
