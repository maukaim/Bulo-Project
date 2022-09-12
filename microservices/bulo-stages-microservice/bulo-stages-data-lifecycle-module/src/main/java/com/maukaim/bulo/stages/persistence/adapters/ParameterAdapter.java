package com.maukaim.bulo.stages.persistence.adapters;

import com.maukaim.bulo.stages.io.models.stages.ParameterDto;
import com.maukaim.bulo.stages.models.stage.Parameter;

public interface ParameterAdapter {
    Parameter adapte(ParameterDto dto);
}
