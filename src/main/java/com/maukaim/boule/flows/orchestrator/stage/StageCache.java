package com.maukaim.boule.flows.orchestrator.stage;

import com.maukaim.boule.flows.orchestrator.stage.Stage;

import java.util.Collection;
import java.util.Optional;

public interface StageCache {
    Optional<Stage> getById(String stageId);
    Collection<Stage> getAll();
}
