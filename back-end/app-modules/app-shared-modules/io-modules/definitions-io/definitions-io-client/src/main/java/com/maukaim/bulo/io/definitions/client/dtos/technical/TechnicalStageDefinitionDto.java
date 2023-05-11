package com.maukaim.bulo.io.definitions.client.dtos.technical;

import com.maukaim.bulo.io.definitions.client.dtos.ParameterDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageDefinitionTypeDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageInputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageOutputDefinitionDto;

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