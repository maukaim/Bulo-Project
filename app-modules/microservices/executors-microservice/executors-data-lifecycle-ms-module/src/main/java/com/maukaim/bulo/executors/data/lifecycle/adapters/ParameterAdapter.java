package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.executors.data.stages.Parameter;
import com.maukaim.bulo.io.executors.system.in.model.ParameterDto;

public interface ParameterAdapter {
    Parameter adapte(ParameterDto dto);
}
