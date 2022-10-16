package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.executors.data.stages.Parameter;
import com.maukaim.bulo.executors.io.in.model.ParameterDto;

public interface ParameterAdapter {
    Parameter adapte(ParameterDto dto);
}
