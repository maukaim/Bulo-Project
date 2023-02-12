package com.maukaim.bulo.standalone.data.lifecycle.runs;

import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class MainStageRunStore implements StageRunStore {
    private final Map<String, StageRun<?>> mappedStageRunId;

    public MainStageRunStore(Map<String, StageRun<?>> initialCache) {
        this.mappedStageRunId = new ConcurrentHashMap<>(initialCache);
    }

    @Override
    public void put(String stageRunId, StageRun<?> technicalStageRun) {
        this.mappedStageRunId.put(stageRunId, technicalStageRun);
    }

    @Override
    public StageRun<?> getById(String stageRunId) {
        return this.mappedStageRunId.get(stageRunId);
    }

    @Override
    public RunContext<?> getContext(String stageRunId) {
        StageRun<?> technicalStageRun = this.mappedStageRunId.get(stageRunId);
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
