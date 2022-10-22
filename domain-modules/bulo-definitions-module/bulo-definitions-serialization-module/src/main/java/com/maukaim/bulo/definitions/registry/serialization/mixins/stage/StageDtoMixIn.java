package com.maukaim.bulo.definitions.registry.serialization.mixins.stage;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maukaim.bulo.definitions.io.stage.FunctionalStageDto;
import com.maukaim.bulo.definitions.io.stage.TechnicalStageDto;

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

