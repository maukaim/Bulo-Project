package com.maukaim.bulo.runs.orchestrators.data.lifecycle;

import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class StageRunStoreImpl implements StageRunStore {
    private Map<String, StageRun> mappedStageRunId = new ConcurrentHashMap<>();

    @Override
    public void put(String stageRunId, StageRun technicalStageRun) {
        this.mappedStageRunId.put(stageRunId, technicalStageRun);
        //TODO: Doit etre fait de maniere a pouvoir les partager entre instances de orchestrator !
    }

    @Override
    public StageRun getById(String stageRunId) {
        return this.mappedStageRunId.get(stageRunId);
    }

    @Override
    public RunContext<?> getContext(String stageRunId) {
        StageRun technicalStageRun = this.mappedStageRunId.get(stageRunId);
        return technicalStageRun == null ? null : technicalStageRun.getContext();
    }

    @Override
    public synchronized FunctionalStageRun compute(String stageRunId, BiFunction<String, FunctionalStageRun, FunctionalStageRun> valueComputer) {
        return (FunctionalStageRun) this.mappedStageRunId.compute(stageRunId, (key,val)->{
            if(val instanceof FunctionalStageRun){
                return valueComputer.apply(key, (FunctionalStageRun) val);
            }else{
                throw new RuntimeException("Trying to compute on Technical StageRun. Method should only be used with FunctionalStageRuns.");
            }
        });
    }
}
