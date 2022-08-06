package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maukaim.bulo.io.stages.FunctionalStageData;
import com.maukaim.bulo.io.stages.TechnicalStageData;

@JsonTypeInfo(
        property = "stageType",
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TechnicalStageData.class, name = "TECHNICAL"),
        @JsonSubTypes.Type(value = FunctionalStageData.class, name = "FUNCTIONAL")
})
public class StageDataMixin {
}

