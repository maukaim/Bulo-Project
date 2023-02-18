package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maukaim.bulo.io.stages.models.stages.FunctionalStageDto;
import com.maukaim.bulo.io.stages.models.stages.TechnicalStageDto;

@JsonTypeInfo(
        property = "stageType",
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TechnicalStageDto.class, name = "TECHNICAL"),
        @JsonSubTypes.Type(value = FunctionalStageDto.class, name = "FUNCTIONAL")
})
public class StageDtoMixIn {
}

