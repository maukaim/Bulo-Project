package com.maukaim.bulo.standalone.data.lifecycle;

import com.maukaim.bulo.runners.api.models.StageDefinition;

public interface StageDefinitionInstructor {
    void create(StageDefinition definition);
    void remove(String stageDefinitionId);
}
