package com.maukaim.bulo.runs.orchestrators.data.definition;

import java.util.Set;

public class FunctionalStageDefinition {
    private final String definitionId;
    private final Set<FsStage> functionalSubStages;

    public FunctionalStageDefinition(String definitionId, Set<FsStage> functionalSubStages) {
        this.definitionId = definitionId;
        this.functionalSubStages = functionalSubStages;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public Set<FsStage> getFunctionalSubStages() {
        return functionalSubStages;
    }

    @Override
    public String toString() {
        return "FunctionalStageDefinition{" +
                "definitionId='" + definitionId + '\'' +
                ", functionalSubStages=" + functionalSubStages +
                '}';
    }
}