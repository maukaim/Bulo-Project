package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.data.models.definition.StageDefinition;
import com.maukaim.bulo.flows.data.models.stage.Stage;

public interface StageParameterValidator {
    void validate(Stage stage, StageDefinition definition) throws FlowValidationException;
}
