package com.maukaim.bulo.stages.core;

import com.maukaim.bulo.io.stages.TechnicalStageData;
import com.maukaim.bulo.stages.core.stage.ValidationReport;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;


public interface TechnicalStageValidator {
    ValidationReport validate(TechnicalStageData stageData, TechnicalStageDefinition definition);
}
