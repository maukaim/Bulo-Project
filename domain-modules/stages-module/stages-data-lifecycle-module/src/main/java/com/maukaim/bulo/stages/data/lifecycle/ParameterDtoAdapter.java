package com.maukaim.bulo.stages.data.lifecycle;

import com.maukaim.bulo.io.stages.models.stages.ParameterDto;
import com.maukaim.bulo.stages.models.stage.Parameter;

public interface ParameterDtoAdapter {
    ParameterDto adapte(Parameter parameter);
}
