package com.maukaim.bulo.mockingbird.builders;

import com.maukaim.bulo.io.definitions.client.dtos.functional.FsStageDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.FunctionalStageDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.InputProviderDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.IoDependencyDto;
import com.maukaim.bulo.io.definitions.client.dtos.functional.OutputProviderDto;
import com.maukaim.bulo.io.data.types.IoTypeDto;
import com.maukaim.bulo.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.io.definitions.client.dtos.ParameterDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageInputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.StageOutputDefinitionDto;
import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FunctionalDefinitionBuilder {
    private Map<String, StageInputDefinitionDto> inputsByName;
    private Map<String, StageOutputDefinitionDto> outputsByName;
    private List<ParameterDefinitionDto> parameterDefinitions;
    private Set<FsStageDto> fsStages;
    private Set<OutputProviderDto> outputProviders;

    public static FunctionalDefinitionBuilder aDefinition() {
        return new FunctionalDefinitionBuilder();
    }

    public static ParameterDefinitionDto paramDefinition(String name, ParameterTypeDto parameterType, String hint, String description) {
        return new ParameterDefinitionDto(name, parameterType, hint, description);
    }

    public static FsStageDto fsStage(ContextStageId contextStageId, Set<IoDependencyDto> ioDependencies) {
        return new FsStageDto(contextStageId, ioDependencies);
    }

    public static IoDependencyDto ioDependency(String inputId, Set<InputProviderDto> inputProviders) {
        return new IoDependencyDto(inputId, inputProviders);
    }

    public static InputProviderDto inputProvider(ContextStageId contextStageId, String... outputIds){
        return new InputProviderDto(contextStageId, Set.of(outputIds));
    }

    public static OutputProviderDto outputProvider(ContextStageId contextStageId, String... outputIds){
        return new OutputProviderDto(contextStageId, Set.of(outputIds));
    }

    private FunctionalDefinitionBuilder() {
    }

    public FunctionalDefinitionBuilder withInput(String inputName, IoTypeDto ioTypeDto) {
        //StageInputDefinitionDto inputDto
        if (inputsByName == null) {
            inputsByName = new HashMap<>();
        }
        inputsByName.put(inputName, new StageInputDefinitionDto(ioTypeDto));
        return this;
    }

    public FunctionalDefinitionBuilder withOutput(String outputName, IoTypeDto ioTypeDto) {
        if (outputsByName == null) {
            outputsByName = new HashMap<>();
        }
        outputsByName.put(outputName, new StageOutputDefinitionDto(ioTypeDto));
        return this;
    }

    public FunctionalDefinitionBuilder withFsStages(FsStageDto... fsStageDtos) {
        fsStages = Set.of(fsStageDtos);
        return this;
    }

    public FunctionalDefinitionBuilder withParameters(ParameterDefinitionDto... parameterDefinitions) {
        this.parameterDefinitions = List.of(parameterDefinitions);
        return this;
    }

    public FunctionalDefinitionBuilder withOutputProviders(OutputProviderDto... outputProviders) {
        this.outputProviders = Set.of(outputProviders);
        return this;
    }

    public FunctionalStageDefinitionDto build() {
        return new FunctionalStageDefinitionDto(
                null,
                inputsByName,
                outputsByName,
                parameterDefinitions,
                fsStages,
                outputProviders
        );
    }
}
