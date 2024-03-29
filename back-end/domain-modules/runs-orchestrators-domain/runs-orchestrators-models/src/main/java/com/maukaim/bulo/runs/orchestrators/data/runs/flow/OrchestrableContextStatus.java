package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunStatus;

import java.util.Set;

public enum OrchestrableContextStatus implements RunStatus {
    NEW("New Run, no task has ran yet."),
    PENDING_START("Root(s) run requested."),
    RUNNING("A Stage is in process. Waiting for execution to finish."),
    PAUSED("Flow execution paused. Need intervention."),
    CANCELLED("Flow execution interrupted during process."),
    FAILED("Flow execution terminated, one or more error(s) raised during the process."),
    SUCCESS("Flow execution terminated, everything ran as expected.");

    public static final Set<OrchestrableContextStatus> NOT_STARTED_STATES = Set.of(NEW, PENDING_START);
    public static final Set<OrchestrableContextStatus> TERMINAL_STATES = Set.of(CANCELLED,FAILED, SUCCESS);
    public static final Set<OrchestrableContextStatus> PROBLEM_STATES = Set.of(CANCELLED,FAILED);
    private final String description;

    OrchestrableContextStatus(String description) {
        this.description = description;
    }

    @Override
    public boolean isTerminal(){
        return TERMINAL_STATES.contains(this);
    }

    @Override
    public boolean isSuccess() {
        return SUCCESS.equals(this);
    }

    @Override
    public boolean isProblem(){
        return PROBLEM_STATES.contains(this);
    }

    @Override
    public boolean isRunNeeded() {
        return this == NEW;
    }
}
