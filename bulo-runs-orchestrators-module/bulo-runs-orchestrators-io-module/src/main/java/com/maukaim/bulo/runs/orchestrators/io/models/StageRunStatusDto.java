package com.maukaim.bulo.runs.orchestrators.io.models;

public enum StageRunStatusDto {
    TO_BE_REQUESTED,
    REQUESTED,
    ACKNOWLEDGED,
    RUNNING,
    TO_BE_CANCELLED,
    CANCELLED,
    FAILED,
    SUCCESS;
}
