package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.executors.data.stages.Parameter;
import com.maukaim.bulo.io.stages.client.model.ParameterDto;

public interface ParameterAdapter {
    Parameter adapte(ParameterDto dto);
}
