package com.maukaim.boule.flows.orchestrator.stage.template;

import com.maukaim.boule.flows.orchestrator.stage.Stage;

import java.util.Map;

public class DummyStage implements Stage {
    private final String stageId;

    public DummyStage(String stageId) {
        this.stageId = stageId;
    }

    @Override
    public String getStageId() {
        return this.stageId;
    }

    @Override
    public String getStageType() {
        return "DUMMY";
    }

    @Override
    public Map<String, String> getParameters() {
        return Map.of();
    }
}
