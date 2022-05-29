package com.maukaim.boule.flows.orchestrator.cache;

import com.maukaim.boule.flows.orchestrator.Run;
import com.maukaim.boule.flows.orchestrator.RunFactory;
import com.maukaim.boule.flows.orchestrator.RunStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class FlowRunCacheImpl implements FlowRunCache {

    private ConcurrentHashMap<String, Run> cache = new ConcurrentHashMap<>();

    @Override
    public Run getRun(String runId) {
        return this.cache.get(runId);
    }

    @Override
    public void updateState(String runId, RunStatus status) {
        this.cache.compute(runId,(runIdKey,run)->{
           if(run != null){
               run = RunFactory.build(run, status);
           }
           return run;
        });
    }

    @Override
    public void add(Run run) {
        this.cache.putIfAbsent(run.getRunId(), run);
    }

    @Override
    public Run remove(String runId) {
        return this.cache.remove(runId);
    }
}
