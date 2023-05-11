package com.maukaim.bulo.definitions.ms.data.lifecycle.adapters;

import com.maukaim.bulo.definitions.data.stage.Parameter;
import com.maukaim.bulo.io.stages.client.model.ParameterDto;

public interface ParameterAdapter {
    Parameter adapte(ParameterDto parameter);
}
