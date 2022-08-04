package com.maukaim.bulo.stages.core.stage;

import com.maukaim.bulo.io.stages.FunctionalStageData;
import com.maukaim.bulo.io.stages.StageData;
import com.maukaim.bulo.io.stages.TechnicalStageData;
import com.maukaim.bulo.stages.core.StageAdapter;
import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.stages.core.TechnicalStageValidator;
import com.maukaim.bulo.stages.models.StageStore;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;
import com.maukaim.bulo.stages.models.stage.Stage;

import java.util.List;

import static com.maukaim.bulo.stages.core.stage.StageCreateReport.DEFAULT_SUCCESS_REPORT;
import static com.maukaim.bulo.stages.core.stage.StageCreateReport.failReport;

public class StageServiceImpl implements StageService {
    private final StageStore stageStore;
    private final TechnicalStageDefinitionService technicalStageDefinitionService;
    private final TechnicalStageValidator technicalStageValidator;
    private final StageAdapter stageAdapter;

    public StageServiceImpl(StageStore stageStore,
                            TechnicalStageDefinitionService technicalStageDefinitionService,
                            TechnicalStageValidator technicalStageValidator,
                            StageAdapter stageAdapter) {
        this.stageStore = stageStore;
        this.technicalStageDefinitionService = technicalStageDefinitionService;
        this.technicalStageValidator = technicalStageValidator;
        this.stageAdapter = stageAdapter;
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
    public StageCreateReport addStage(StageData stageData) {
        return switch (stageData.getStageType()) {
            case TECHNICAL -> addTechnicalStage((TechnicalStageData) stageData);
            case FUNCTIONAL -> addFunctionalStage((FunctionalStageData) stageData);
            default -> throw new RuntimeException("Not supported StageType: " + stageData.getStageType());
        };
    }

    private StageCreateReport addFunctionalStage(FunctionalStageData stageData) {
        this.stageStore.put(this.stageAdapter.adapte(stageData));
        return DEFAULT_SUCCESS_REPORT;
    }

    private StageCreateReport addTechnicalStage(TechnicalStageData stageData) {
        String definitionId = stageData.getDefinitionId();
        if (definitionId == null) {
            return failReport("No definition identifier provided to create the TechnicalStage. Impossible to validate.");
        }

        TechnicalStageDefinition definition = this.technicalStageDefinitionService.getById(definitionId);
        if (definition == null) {
            return failReport(String.format("No definition found with identifier %s", definitionId));
        }

        ValidationReport validationReport = technicalStageValidator.validate(stageData, definition);
        if (validationReport.isValidated()) {
            this.stageStore.put(this.stageAdapter.adapte(stageData));
            return DEFAULT_SUCCESS_REPORT;
        } else {
            return failReport(validationReport.getDetails());
        }

    }
}
