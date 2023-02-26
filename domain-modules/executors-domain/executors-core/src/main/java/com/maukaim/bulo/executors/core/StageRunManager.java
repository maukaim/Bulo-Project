package com.maukaim.bulo.executors.core;

public interface StageRunManager {
    boolean submit(StageRunConfig stageRunConfig);
    boolean cancel(String stageRunId);

}
