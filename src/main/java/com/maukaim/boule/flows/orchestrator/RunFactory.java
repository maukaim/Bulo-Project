package com.maukaim.boule.flows.orchestrator;

import java.util.concurrent.Flow;

public class RunFactory {

    public static Run build (Run run, RunStatus newStatus){
        return new Run(run.getRunId(), run.getFlowId(), run.getCurrentExecutionId(), newStatus);
    }

    public static Run newRun (Flow){
        return new Run(run.getRunId(), run.getFlowId(), run.getCurrentExecutionId(), newStatus);
    }
}
