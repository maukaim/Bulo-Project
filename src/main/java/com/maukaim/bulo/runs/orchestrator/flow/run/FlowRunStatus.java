package com.maukaim.bulo.runs.orchestrator.flow.run;

import java.util.Set;

public enum FlowRunStatus {
    NEW("New Run, no task has ran yet."),
    PENDING_START("Root(s) run requested."),
    RUNNING("A Stage is in process. Waiting for execution to finish."),
    PAUSED("Flow execution paused. Need intervention."),
    CANCELLED("Flow execution interrupted during process."),
    FAILED("Flow execution terminated, one or more error(s) raised during the process."),
    SUCCESS("Flow execution terminated, everything ran as expected.");

    public static final Set<FlowRunStatus> NOT_STARTED_STATES = Set.of(NEW, PENDING_START);
    public static final Set<FlowRunStatus> TERMINAL_STATES = Set.of(CANCELLED,FAILED, SUCCESS);
    public static final Set<FlowRunStatus> PROBLEM_STATES = Set.of(CANCELLED,FAILED);
    private final String description;

    FlowRunStatus(String description) {
        this.description = description;
    }

    public boolean isTerminal(){
        return TERMINAL_STATES.contains(this);
    }

    public boolean isProblem(){
        return PROBLEM_STATES.contains(this);
    }
}
