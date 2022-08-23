package com.maukaim.bulo.executors.data.lifecycle.resolver;

import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.executors.io.out.*;

import java.time.Instant;

public class StageRunEventResolverImpl implements StageRunEventResolver {
    @Override
    public StageRunEvent resolve(StageRunResult stageRunResult) {
         switch(stageRunResult.getStatus()){
             case ACKNOWLEDGED -> {
                 return new AcknowledgeStageRunEvent(null, stageRunResult.getStageRunId(), Instant.now());
             }
             case RUNNING -> {
                 return new StartRunStageRunEvent(stageRunResult.getStageRunId(), Instant.now());
             }
             case CANCELLED -> {
                 return new RunCancelledStageRunEvent(stageRunResult.getStageRunId(), Instant.now());
             }
             case FAILED -> {
                 return new RunFailedStageRunEvent(stageRunResult.getStageRunId(), Instant.now());
             }
             case SUCCESS -> {
                 return new RunSuccessfulStageRunEvent(stageRunResult.getStageRunId(), Instant.now());
             }
             default -> throw new RuntimeException("Undefined event for this run status: "+ stageRunResult.getStageRunId());
         }
    }
}
