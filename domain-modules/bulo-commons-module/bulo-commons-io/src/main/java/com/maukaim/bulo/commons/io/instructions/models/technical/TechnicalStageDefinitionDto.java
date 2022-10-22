package com.maukaim.bulo.commons.io.instructions.models.technical;

import com.maukaim.bulo.commons.io.instructions.models.*;

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
                "id='" + id + '\'' +
                ", inputsByName=" + inputsByName +
                ", outputsByName=" + outputsByName +
                ", parameters=" + parameters +
                ", stageDefinitionType=" + stageDefinitionType +
                '}';
    }
}