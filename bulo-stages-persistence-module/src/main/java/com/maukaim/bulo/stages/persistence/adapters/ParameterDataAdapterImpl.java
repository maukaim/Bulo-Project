package com.maukaim.bulo.stages.persistence.adapters;

import com.maukaim.bulo.io.stages.ParameterData;
import com.maukaim.bulo.stages.models.stage.Parameter;

public class ParameterDataAdapterImpl implements ParameterDataAdapter {
    @Override
    public ParameterData adapte(Parameter parameter) {
        return new ParameterData(
                parameter.getValue(),
                parameter.getName(),
                parameter.getValueType(),
                parameter.getAdditionalDetails()
        );
    }
}
