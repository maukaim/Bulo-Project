package com.maukaim.bulo.stages.core;

import com.maukaim.bulo.stages.core.validators.ValidationReport;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;


public interface TechnicalStageValidator {
    ValidationReport validate(TechnicalStage actualStage, StageDefinition definition);
}
