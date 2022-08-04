package com.maukaim.bulo.stages.persistence.adapters;

import com.maukaim.bulo.io.stages.ParameterData;
import com.maukaim.bulo.stages.models.stage.Parameter;

public interface ParameterDataAdapter {

    ParameterData adapte(Parameter parameter);
}
