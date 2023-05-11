package com.maukaim.bulo.serialization.runs.orchestrators.system.mixins.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maukaim.bulo.io.runs.orchestrators.system.models.FunctionalStageRunDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.TechnicalStageRunDto;

@JsonTypeInfo(
        property = "stageType",
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TechnicalStageRunDto.class, name = "TECHNICAL"),
        @JsonSubTypes.Type(value = FunctionalStageRunDto.class, name = "FUNCTIONAL")

})
public class StageRunDtoMixIn {
}
