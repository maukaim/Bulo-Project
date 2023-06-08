package com.maukaim.bulo.stages.core.impl;

import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.StageDefinitionService;
import com.maukaim.bulo.stages.core.validators.StageCreateReport;
import com.maukaim.bulo.stages.core.StageValidator;
import com.maukaim.bulo.stages.core.validators.ValidationReport;
import com.maukaim.bulo.stages.models.StageStore;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import com.maukaim.bulo.stages.models.stage.FunctionalStage;
import com.maukaim.bulo.stages.models.stage.Stage;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;

import java.util.List;

import static com.maukaim.bulo.stages.core.validators.StageCreateReport.*;

public class StageServiceImpl implements StageService {
    private final StageStore stageStore;
    private final StageDefinitionService stageDefinitionService;
    private final StageValidator stageValidator;

    public StageServiceImpl(StageStore stageStore,
                            StageDefinitionService stageDefinitionService,
                            StageValidator stageValidator) {
        this.stageStore = stageStore;
        this.stageDefinitionService = stageDefinitionService;
        this.stageValidator = stageValidator;
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
        };
    }

    private StageCreateReport addFunctionalStage(FunctionalStage stage) {
        this.stageStore.put(stage);
        return successReport(stage.getStageId(), DEFAULT_SUCCESS_REPORT);
    }

    private StageCreateReport addTechnicalStage(TechnicalStage stage) {
        String definitionId = stage.getDefinitionId();
        if (definitionId == null) {
            return failReport(stage.getStageId(), "No definition identifier provided to create the TechnicalStage. Impossible to validate.");
        }

        StageDefinition definition = this.stageDefinitionService.getById(definitionId);
        if (definition == null) {
            return failReport(stage.getStageId(), String.format("No definition found with identifier %s", definitionId));
        }

        ValidationReport validationReport = stageValidator.validate(stage, definition);
        if (validationReport.isValidated()) {
            this.stageStore.put(stage);
            return successReport(stage.getStageId(), DEFAULT_SUCCESS_REPORT);
        } else {
            return failReport(stage.getStageId(), validationReport.getDetails());
        }

    }
}
