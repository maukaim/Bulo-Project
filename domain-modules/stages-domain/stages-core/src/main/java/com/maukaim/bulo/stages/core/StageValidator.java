package com.maukaim.bulo.stages.core;

import com.maukaim.bulo.stages.core.validators.ValidationReport;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import com.maukaim.bulo.stages.models.stage.Stage;

public interface StageValidator {
    ValidationReport validate(Stage actualStage, StageDefinition definition);
}
