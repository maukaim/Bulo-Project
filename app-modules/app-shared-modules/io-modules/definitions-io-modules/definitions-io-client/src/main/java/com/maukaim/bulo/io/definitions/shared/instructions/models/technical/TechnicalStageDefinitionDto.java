package com.maukaim.bulo.io.definitions.shared.instructions.models.technical;

import com.maukaim.bulo.io.definitions.shared.instructions.models.ParameterDefinitionDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.StageDefinitionDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.StageDefinitionTypeDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.StageInputDefinitionDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.StageOutputDefinitionDto;

import java.util.List;
import java.util.Map;

public class TechnicalStageDefinitionDto extends StageDefinitionDto {

    public TechnicalStageDefinitionDto(String id,
                                       Map<String, StageInputDefinitionDto> inputsByName,
                                       Map<String, StageOutputDefinitionDto> outputsByName,
                                       List<ParameterDefinitionDto> parameters) {
        super(id,inputsByName,outputsByName,parameters, StageDefinitionTypeDto.TECHNICAL);

    }

    @Override
    public String toString() {
        return "TechnicalStageDefinitionDto{" +
                "id='" + definitionId + '\'' +
                ", inputsByName=" + inputsByName +
                ", outputsByName=" + outputsByName +
                ", parameters=" + parameters +
                ", stageDefinitionType=" + stageDefinitionType +
                '}';
    }
}