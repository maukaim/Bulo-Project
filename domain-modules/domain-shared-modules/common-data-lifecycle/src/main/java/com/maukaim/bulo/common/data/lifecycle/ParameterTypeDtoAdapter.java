package com.maukaim.bulo.common.data.lifecycle;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.io.data.types.ParameterTypeDto;

public interface ParameterTypeDtoAdapter {
    ParameterTypeDto adapte(ParameterType parameterType);
}
