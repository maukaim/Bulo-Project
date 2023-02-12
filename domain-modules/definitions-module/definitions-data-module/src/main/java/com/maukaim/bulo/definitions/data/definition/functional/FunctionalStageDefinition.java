package com.maukaim.bulo.definitions.data.definition.functional;

import com.maukaim.bulo.commons.models.definitions.StageDefinitionType;
import com.maukaim.bulo.definitions.data.definition.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class FunctionalStageDefinition extends StageDefinition {
    private final Set<FsStage> functionalSubStages;
    private final Set<OutputProvider> outputProviders;

    public FunctionalStageDefinition(String id,
                                     Map<String, StageInputDefinition> inputsByName,
                                     Map<String, StageOutputDefinition> outputsByName,
                                     List<ParameterDefinition> parameters,
                                     Set<FsStage> functionalSubStages,
                                     Set<OutputProvider> outputProviders) {
        super(id, inputsByName, outputsByName, parameters, StageDefinitionType.FUNCTIONAL);
        this.functionalSubStages = functionalSubStages;
        this.outputProviders = outputProviders;
    }

    public Set<FsStage> getFunctionalSubStages() {
        return functionalSubStages;
    }

    public Set<OutputProvider> getOutputProviders() {
        return outputProviders;
    }

    @Override
    public String toString() {
        return "FunctionalStageDefinition{" +
                "functionalSubStages=" + functionalSubStages +
                ", outputProviders=" + outputProviders +
                ", id='" + id + '\'' +
                ", inputsByName=" + inputsByName +
                ", outputsByName=" + outputsByName +
                ", parameters=" + parameters +
                ", stageDefinitionType=" + stageDefinitionType +
                '}';
    }
}