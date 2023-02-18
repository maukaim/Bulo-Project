package com.maukaim.bulo.mockingbird.builders;
import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.io.stages.client.model.FunctionalStageDto;
import com.maukaim.bulo.io.stages.client.model.ParameterDto;
import com.maukaim.bulo.io.stages.client.model.StageDto;
import com.maukaim.bulo.io.stages.client.model.TechnicalStageDto;

import java.util.ArrayList;
import java.util.List;

public class StageDtoBuilder {
    protected final StageType stageType;
    protected List<ParameterDto> parameters;
    protected String definitionId;

    public static StageDtoBuilder aFunctionalStage(){
        return new StageDtoBuilder(StageType.FUNCTIONAL);
    }

    public static StageDtoBuilder aTechnicalStage(){
        return new StageDtoBuilder(StageType.TECHNICAL);
    }

    private StageDtoBuilder(StageType stageType) {
        this.stageType = stageType;
    }

    private StageDtoBuilder(StageType stageType, List<ParameterDto> parameters, String definitionId) {
        this.stageType = stageType;
        this.parameters = parameters;
        this.definitionId = definitionId;
    }

    public StageDtoBuilder withParameters(ParameterDto... parameterDtos) {
        if(this.parameters == null){
            this.parameters = new ArrayList<>();
        }

        for (ParameterDto parameter : parameterDtos) {
            this.parameters.add(parameter);
        }
        return this;
    }

    public StageDtoBuilder withParameter(String name, String value, String details) {
        ParameterDto dto = new ParameterDto(value, name, details);
        return withParameters(dto);
    }

    public StageDtoBuilder withEmptyParameter() {
        this.parameters = List.of();
        return this;
    }

    public StageDtoBuilder withDefinitionId(String definitionId) {
        this.definitionId = definitionId;
        return this;
    }

    public StageDto build(){
        return switch (stageType){
            case TECHNICAL -> new TechnicalStageDto(null, parameters, definitionId);
            case FUNCTIONAL -> new FunctionalStageDto(null, parameters, definitionId);
        };
    }

}
