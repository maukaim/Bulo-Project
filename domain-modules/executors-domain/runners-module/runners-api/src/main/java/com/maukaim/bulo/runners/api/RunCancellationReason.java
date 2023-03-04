package com.maukaim.bulo.runners.api;

public enum RunCancellationReason {
    JSON_DESERIALIZATION("Problem encoutered during deserialization"),
    UNDEFINED("No particular reason provided.");

    private final String description;

    RunCancellationReason(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
