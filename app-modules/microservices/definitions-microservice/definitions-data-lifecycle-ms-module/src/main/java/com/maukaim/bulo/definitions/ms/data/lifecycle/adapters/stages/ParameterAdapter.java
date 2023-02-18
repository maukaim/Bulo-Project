package com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.stages;

import com.maukaim.bulo.definitions.data.stage.Parameter;
import com.maukaim.bulo.io.definitions.stage.ParameterDto;

public interface ParameterAdapter {
    Parameter adapte(ParameterDto parameter);
}
