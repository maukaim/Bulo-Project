package com.maukaim.bulo.commons.io.instructions.models.functional;

import com.maukaim.bulo.commons.io.instructions.models.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class FunctionalStageDefinitionDto extends StageDefinitionDto {
    private final Set<FsStageDto> functionalSubStages;

    public FunctionalStageDefinitionDto(String id,
                                        Map<String, StageInputDefinitionDto> inputsByName,
                                        Map<String, StageOutputDefinitionDto> outputsByName,
                                        List<ParameterDefinitionDto> parameters,
                                        Set<FsStageDto> functionalSubStages) {
        super(id, inputsByName, outputsByName, parameters, StageDefinitionTypeDto.FUNCTIONAL);
        this.functionalSubStages = functionalSubStages;
    }

    public Set<FsStageDto> getFunctionalSubStages() {
        return functionalSubStages;
    }

    @Override
    public String toString() {
        return "FunctionalStageDefinitionDto{" +
                "functionalSubStages=" + functionalSubStages +
                ", id='" + id + '\'' +
                ", inputsByName=" + inputsByName +
                ", outputsByName=" + outputsByName +
                ", parameters=" + parameters +
                ", stageDefinitionType=" + stageDefinitionType +
                '}';
    }
}
