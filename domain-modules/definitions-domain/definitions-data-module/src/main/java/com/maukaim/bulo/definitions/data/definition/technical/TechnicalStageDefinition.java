package com.maukaim.bulo.definitions.data.definition.technical;

import com.maukaim.bulo.commons.models.definitions.StageDefinitionType;
import com.maukaim.bulo.definitions.data.definition.*;

import java.util.List;
import java.util.Map;

public class TechnicalStageDefinition extends StageDefinition {

    public TechnicalStageDefinition(String technicalStageDefinitionId,
                                    Map<String, StageInputDefinition> inputsByName,
                                    Map<String, StageOutputDefinition> outputsByName,
                                    List<ParameterDefinition> parameters) {
        super(technicalStageDefinitionId, inputsByName, outputsByName, parameters, StageDefinitionType.TECHNICAL);
    }
}