package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

public interface RunStatus {
    boolean isTerminal();
    boolean isSuccess();
    boolean isProblem();
    boolean isRunNeeded();
}
