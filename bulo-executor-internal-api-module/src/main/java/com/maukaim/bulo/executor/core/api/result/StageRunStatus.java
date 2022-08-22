package com.maukaim.bulo.executor.core.api.result;

import java.util.Set;

public enum StageRunStatus {
    ACKNOWLEDGED,
    RUNNING,
    CANCELLED,
    FAILED,
    SUCCESS;

    public static final Set<StageRunStatus> TERMINAL_STATES = Set.of(SUCCESS, FAILED, CANCELLED);

    public boolean isTerminal(){
        return TERMINAL_STATES.contains(this);
    }
}
