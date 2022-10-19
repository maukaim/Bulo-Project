package com.maukaim.bulo.definitions.data.technical;

import com.maukaim.bulo.definitions.data.ParameterDefinition;
import com.maukaim.bulo.definitions.data.StageDefinition;
import com.maukaim.bulo.definitions.data.StageInputDefinition;
import com.maukaim.bulo.definitions.data.StageOutputDefinition;

import java.util.List;
import java.util.Map;

public class TechnicalStageDefinition extends StageDefinition {

    public TechnicalStageDefinition(String technicalStageDefinitionId,
                                    Map<String, StageInputDefinition> inputsByName,
                                    Map<String, StageOutputDefinition> outputsByName,
                                    List<ParameterDefinition> parameters) {
        super(technicalStageDefinitionId, inputsByName, outputsByName, parameters);
    }
}