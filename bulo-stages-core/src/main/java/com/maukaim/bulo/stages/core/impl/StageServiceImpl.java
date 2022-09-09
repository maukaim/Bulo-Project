package com.maukaim.bulo.stages.core.impl;

import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.stages.core.validators.StageCreateReport;
import com.maukaim.bulo.stages.core.validators.TechnicalStageValidator;
import com.maukaim.bulo.stages.core.validators.ValidationReport;
import com.maukaim.bulo.stages.models.StageStore;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;
import com.maukaim.bulo.stages.models.stage.FunctionalStage;
import com.maukaim.bulo.stages.models.stage.Stage;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;

import java.util.List;

import static com.maukaim.bulo.stages.core.validators.StageCreateReport.DEFAULT_SUCCESS_REPORT;
import static com.maukaim.bulo.stages.core.validators.StageCreateReport.failReport;

public class StageServiceImpl implements StageService {
    private final StageStore stageStore;
    private final TechnicalStageDefinitionService technicalStageDefinitionService;
    private final TechnicalStageValidator technicalStageValidator;

    public StageServiceImpl(StageStore stageStore,
                            TechnicalStageDefinitionService technicalStageDefinitionService,
                            TechnicalStageValidator technicalStageValidator) {
        this.stageStore = stageStore;
        this.technicalStageDefinitionService = technicalStageDefinitionService;
        this.technicalStageValidator = technicalStageValidator;
    }

    @Override
    public List<Stage> getAll() {
        return this.stageStore.getAll();
    }

    @Override
    public Stage getById(String id) {
        return this.stageStore.getById(id);
    }

    @Override
    public Stage remove(String stageId) {
        Stage stage = this.stageStore.getById(stageId);
        if (stage != null) {
            this.stageStore.remove(stage);
        }
        return stage;
    }

    @Override
    public StageCreateReport addStage(Stage stage) {
        return switch (stage.getStageType()) {
            case TECHNICAL -> addTechnicalStage((TechnicalStage) stage);
            case FUNCTIONAL -> addFunctionalStage((FunctionalStage) stage);
            default -> throw new RuntimeException("Not supported StageType: " + stage.getStageType());
        };
    }

    private StageCreateReport addFunctionalStage(FunctionalStage stage) {
        this.stageStore.put(stage);
        return DEFAULT_SUCCESS_REPORT;
    }

    private StageCreateReport addTechnicalStage(TechnicalStage stage) {
        String definitionId = stage.getDefinitionId();
        if (definitionId == null) {
            return failReport("No definition identifier provided to create the TechnicalStage. Impossible to validate.");
        }

        TechnicalStageDefinition definition = this.technicalStageDefinitionService.getById(definitionId);
        if (definition == null) {
            return failReport(String.format("No definition found with identifier %s", definitionId));
        }

        ValidationReport validationReport = technicalStageValidator.validate(stage, definition);
        if (validationReport.isValidated()) {
            this.stageStore.put(stage);
            return DEFAULT_SUCCESS_REPORT;
        } else {
            return failReport(validationReport.getDetails());
        }

    }
}
