package com.maukaim.bulo.io.stages;


import com.maukaim.bulo.commons.models.StageType;

import java.util.List;

public class FunctionalStageData extends StageData {
    public FunctionalStageData(List<ParameterData> parameters) {
        super(StageType.FUNCTIONAL, parameters);
    }

    @Override
    public String toString() {
        return "FunctionalStageData{" +
                "stageType=" + stageType +
                ", parameters=" + parameters +
                '}';
    }
}
