package com.maukaim.bulo.stages.persistence.adapters.impl;

import com.maukaim.bulo.stages.io.models.stages.ParameterDto;
import com.maukaim.bulo.stages.models.stage.Parameter;
import com.maukaim.bulo.stages.persistence.adapters.ParameterDataAdapter;

public class ParameterDataAdapterImpl implements ParameterDataAdapter {
    @Override
    public ParameterDto adapte(Parameter parameter) {
        return new ParameterDto(
                parameter.getValue(),
                parameter.getName(),
                parameter.getValueType(),
                parameter.getAdditionalDetails()
        );
    }
}
