package com.maukaim.bulo.data.lifecycle.data.types.client;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.io.data.types.ParameterTypeDto;

public interface ParameterTypeAdapter {
    ParameterType adapte(ParameterTypeDto dto);
}
