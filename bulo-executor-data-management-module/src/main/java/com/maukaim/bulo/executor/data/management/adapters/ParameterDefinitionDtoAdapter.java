package com.maukaim.bulo.executor.data.management.adapters;

import com.maukaim.bulo.executor.core.api.models.ParameterDefinition;
import com.maukaim.bulo.executor.core.api.models.TechnicalStageOutputDefinition;
import com.maukaim.bulo.executor.io.out.model.ParameterDefinitionDto;
import com.maukaim.bulo.executor.io.out.model.StageOutputDefinitionDto;

public interface ParameterDefinitionDtoAdapter {
    ParameterDefinitionDto adapte(ParameterDefinition parameterDefinition);
}
