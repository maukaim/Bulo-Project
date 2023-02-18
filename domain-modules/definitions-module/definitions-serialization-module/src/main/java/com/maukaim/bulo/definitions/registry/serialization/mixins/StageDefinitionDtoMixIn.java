package com.maukaim.bulo.definitions.registry.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maukaim.bulo.io.definitions.client.dtos.functional.FunctionalStageDefinitionDto;
import com.maukaim.bulo.io.definitions.client.dtos.technical.TechnicalStageDefinitionDto;

@JsonTypeInfo(
        property = "stageDefinitionType",
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TechnicalStageDefinitionDto.class, name = "TECHNICAL"),
        @JsonSubTypes.Type(value = FunctionalStageDefinitionDto.class, name = "FUNCTIONAL")
})
public class StageDefinitionDtoMixIn {
}
