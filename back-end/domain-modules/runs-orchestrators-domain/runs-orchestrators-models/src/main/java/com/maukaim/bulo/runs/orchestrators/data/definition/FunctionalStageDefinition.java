package com.maukaim.bulo.runs.orchestrators.data.definition;

import java.util.Set;

public class FunctionalStageDefinition {
    private final String definitionId;
    private final Set<FsStage> functionalSubStages;
    private final Set<OutputProvider> outputProviders;

    public FunctionalStageDefinition(String definitionId,
                                     Set<FsStage> functionalSubStages,
                                     Set<OutputProvider> outputProviders) {
        this.definitionId = definitionId;
        this.functionalSubStages = functionalSubStages;
        this.outputProviders = outputProviders;
    }

    public String getDefinitionId() {
        return definitionId;
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
                "definitionId='" + definitionId + '\'' +
                ", functionalSubStages=" + functionalSubStages +
                ", outputProviders=" + outputProviders +
                '}';
    }
}