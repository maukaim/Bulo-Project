package com.maukaim.bulo.executors.data.lifecycle.resolver;

import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.io.executors.system.out.AcknowledgeStageRunEvent;
import com.maukaim.bulo.io.executors.system.out.RunCancelledStageRunEvent;
import com.maukaim.bulo.io.executors.system.out.RunFailedStageRunEvent;
import com.maukaim.bulo.io.executors.system.out.RunSuccessfulStageRunEvent;
import com.maukaim.bulo.io.executors.system.out.StageRunEvent;
import com.maukaim.bulo.io.executors.system.out.StartRunStageRunEvent;

import java.time.Instant;

public class StageRunEventResolverImpl implements StageRunEventResolver {
    @Override
    public StageRunEvent resolve(StageRunResult stageRunResult) {
         return switch(stageRunResult.getStatus()){
             case ACKNOWLEDGED -> new AcknowledgeStageRunEvent("JeSuisLExecutorId", stageRunResult.getStageRunId(), Instant.now());
             case RUNNING -> new StartRunStageRunEvent(stageRunResult.getStageRunId(), Instant.now());
             case CANCELLED -> new RunCancelledStageRunEvent(stageRunResult.getStageRunId(), Instant.now());
             case FAILED -> new RunFailedStageRunEvent(stageRunResult.getStageRunId(), Instant.now());
             case SUCCESS -> new RunSuccessfulStageRunEvent(stageRunResult.getStageRunId(), Instant.now());
         };
    }
}
