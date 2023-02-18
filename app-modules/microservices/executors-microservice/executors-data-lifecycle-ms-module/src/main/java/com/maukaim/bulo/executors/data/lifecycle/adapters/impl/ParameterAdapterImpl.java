package com.maukaim.bulo.executors.data.lifecycle.adapters.impl;

import com.maukaim.bulo.executors.data.lifecycle.adapters.ParameterAdapter;
import com.maukaim.bulo.executors.data.stages.Parameter;
import com.maukaim.bulo.io.executors.system.in.model.ParameterDto;

public class ParameterAdapterImpl implements ParameterAdapter {
    @Override
    public Parameter adapte(ParameterDto dto) {
        return new Parameter(
                dto.getValue(),
                dto.getName(),
                dto.getAdditionalDetails()
        );
    }
}
