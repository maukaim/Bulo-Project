package com.maukaim.bulo.stages.persistence.adapters.impl;

import com.maukaim.bulo.stages.io.models.stages.ParameterDto;
import com.maukaim.bulo.stages.models.stage.Parameter;
import com.maukaim.bulo.stages.persistence.adapters.ParameterAdapter;

public class ParameterAdapterImpl implements ParameterAdapter {
    @Override
    public Parameter adapte(ParameterDto dto) {
        return new Parameter(
                dto.getValue(),
                dto.getName(),
                dto.getValueType(),
                dto.getAdditionalDetails()
        );
    }
}
