package com.maukaim.bulo.stages.core.stage;

import com.maukaim.bulo.io.stages.ParameterData;
import com.maukaim.bulo.stages.core.ParameterAdapter;
import com.maukaim.bulo.stages.models.stage.Parameter;

public class ParameterAdapterImpl implements ParameterAdapter {
    @Override
    public Parameter adapte(ParameterData paramData) {
        return new Parameter(
                paramData.getValue(),
                paramData.getName(),
                paramData.getValueType(),
                paramData.getAdditionalDetails()
        );
    }
}
