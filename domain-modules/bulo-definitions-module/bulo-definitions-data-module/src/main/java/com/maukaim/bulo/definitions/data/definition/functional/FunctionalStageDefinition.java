package com.maukaim.bulo.definitions.data.definition.functional;

import com.maukaim.bulo.definitions.data.definition.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class FunctionalStageDefinition extends StageDefinition {
    private final Set<FsStage> functionalSubStages;

    public FunctionalStageDefinition(String id,
                                     Map<String, StageInputDefinition> inputsByName,
                                     Map<String, StageOutputDefinition> outputsByName,
                                     List<ParameterDefinition> parameters,
                                     Set<FsStage> functionalSubStages) {
        super(id, inputsByName, outputsByName, parameters, StageDefinitionType.FUNCTIONAL);
        this.functionalSubStages = functionalSubStages;
    }

    public Set<FsStage> getFunctionalSubStages() {
        return functionalSubStages;
    }

    @Override
    public String toString() {
        return "FunctionalStageDefinition{" +
                "functionalSubStages=" + functionalSubStages +
                ", id='" + id + '\'' +
                ", inputsByName=" + inputsByName +
                ", outputsByName=" + outputsByName +
                ", parameters=" + parameters +
                '}';
    }
}