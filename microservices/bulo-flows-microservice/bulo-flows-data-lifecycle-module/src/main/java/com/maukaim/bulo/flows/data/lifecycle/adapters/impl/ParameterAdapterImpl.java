package com.maukaim.bulo.flows.data.lifecycle.adapters.impl;


import com.maukaim.bulo.flows.data.lifecycle.adapters.ParameterAdapter;
import com.maukaim.bulo.flows.data.models.stage.Parameter;
import com.maukaim.bulo.flows.io.stage.ParameterDto;

public class ParameterAdapterImpl implements ParameterAdapter {
    @Override
    public Parameter adapte(ParameterDto dto) {
        return new Parameter(
                dto.getValue(),
                dto.getName(),
                dto.getValueType()
        );
    }
}
