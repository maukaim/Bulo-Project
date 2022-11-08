package com.maukaim.bulo.runs.orchestrators.data.runs.stage;

import java.util.Set;

public enum TechnicalStageRunStatus implements RunStatus{
    TO_BE_REQUESTED("Stage run to be requested soon."),
    REQUESTED("Stage run requested."),
    ACKNOWLEDGED("Stage Run acknowledged by a StageExecutor. In Process to be run."),
    RUNNING("StageExecutor started to run."),
    TO_BE_CANCELLED("Stage cancel requested."),
    CANCELLED("Stage cancelled."),
    FAILED("Stage run is failed"),
    SUCCESS("Stage run is successful.");

    private final String description;

    TechnicalStageRunStatus(String description) {
        this.description = description;
    }

    private static Set<TechnicalStageRunStatus> TERMINAL_STATES = Set.of(CANCELLED, FAILED, SUCCESS);
    private static Set<TechnicalStageRunStatus> PROBLEM_STATES = Set.of(CANCELLED, FAILED);

    @Override
    public boolean isTerminal() {
        return TERMINAL_STATES.contains(this);
    }

    @Override
    public boolean isSuccess() {
        return SUCCESS.equals(this);
    }

    @Override
    public boolean isProblem() {
        return PROBLEM_STATES.contains(this);
    }

    @Override
    public boolean isRunNeeded() {
        return this == TO_BE_REQUESTED;
    }

    public TechnicalStageRunStatus resolveComparedTo(TechnicalStageRunStatus other){
        return other != null && other.isTerminal() ? other : this;
    }

    public String getDescription() {
        return description;
    }
}
