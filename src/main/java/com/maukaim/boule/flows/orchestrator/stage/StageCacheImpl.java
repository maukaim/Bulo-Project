package com.maukaim.boule.flows.orchestrator.stage;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.maukaim.boule.flows.orchestrator.factory.FakeContextProvider.*;

public class StageCacheImpl implements StageCache {

    private final ConcurrentHashMap<String, Stage> stageById = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        this.stageById.put(STAGE_1.getStageId(), STAGE_1);
        this.stageById.put(STAGE_2.getStageId(), STAGE_2);
        this.stageById.put(STAGE_3.getStageId(), STAGE_3);
        this.stageById.put(STAGE_4.getStageId(), STAGE_4);
        this.stageById.put(STAGE_5.getStageId(), STAGE_5);
    }

    @Override
    public Optional<Stage> getById(String stageId) {
        return Optional.of(this.stageById.get(stageId));
    }

    @Override
    public Collection<Stage> getAll() {
        return null;
    }
}
