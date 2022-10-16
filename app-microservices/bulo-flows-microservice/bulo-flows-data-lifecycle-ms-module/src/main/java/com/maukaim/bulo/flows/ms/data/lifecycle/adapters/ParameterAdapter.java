package com.maukaim.bulo.flows.ms.data.lifecycle.adapters;


import com.maukaim.bulo.flows.data.models.stage.Parameter;
import com.maukaim.bulo.flows.io.stage.ParameterDto;

public interface ParameterAdapter {
    Parameter adapte(ParameterDto parameter);
}
