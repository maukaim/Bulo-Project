package com.maukaim.bulo.io.definitions.client.dtos.functional;

import com.maukaim.bulo.io.definitions.client.dtos.ParameterDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageDefinitionTypeDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageInputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageOutputDefinitionDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class FunctionalStageDefinitionDto extends StageDefinitionDto {
    private final Set<FsStageDto> functionalSubStages;
    private final Set<OutputProviderDto> outputProviders;

    public FunctionalStageDefinitionDto(String id,
                                        Map<String, StageInputDefinitionDto> inputsByName,
                                        Map<String, StageOutputDefinitionDto> outputsByName,
                                        List<ParameterDefinitionDto> parameters,
                                        Set<FsStageDto> functionalSubStages,
                                        Set<OutputProviderDto> outputProviders) {
        super(id, inputsByName, outputsByName, parameters, StageDefinitionTypeDto.FUNCTIONAL);
        this.functionalSubStages = functionalSubStages;
        this.outputProviders = outputProviders;
    }

    public Set<FsStageDto> getFunctionalSubStages() {
        return functionalSubStages;
    }

    public Set<OutputProviderDto> getOutputProviders() {
        return outputProviders;
    }

    @Override
    public String toString() {
        return "FunctionalStageDefinitionDto{" +
                "functionalSubStages=" + functionalSubStages +
                ", outputProviders=" + outputProviders +
                ", definitionId='" + definitionId + '\'' +
                ", inputsByName=" + inputsByName +
                ", outputsByName=" + outputsByName +
                ", parameters=" + parameters +
                ", stageDefinitionType=" + stageDefinitionType +
                '}';
    }
}
