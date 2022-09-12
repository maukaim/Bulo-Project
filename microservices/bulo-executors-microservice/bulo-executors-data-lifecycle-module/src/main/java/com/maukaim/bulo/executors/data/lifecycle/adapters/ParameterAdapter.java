package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.executors.data.stages.Parameter;
import com.maukaim.bulo.executors.data.stages.Stage;
import com.maukaim.bulo.executors.io.in.model.ParameterDto;
import com.maukaim.bulo.executors.io.in.model.TechnicalStageDto;

public interface ParameterAdapter {
    Parameter adapte(ParameterDto dto);
}
