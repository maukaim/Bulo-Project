package com.maukaim.bulo.runners.api;

import static com.maukaim.bulo.runners.api.RunCancellationReason.JSON_DESERIALIZATION;
import static com.maukaim.bulo.runners.api.RunCancellationReason.UNDEFINED;

public class ExecutionCancelledException extends RuntimeException{
    private final RunCancellationReason reason;

    private final String additionalDetails;

    public static ExecutionCancelledException jsonDeserialization(String details){
        return new ExecutionCancelledException(JSON_DESERIALIZATION, details);
    }

    /**
     * Use when Reason to Fail is not supported in the list.
     * @param details
     * @return
     */
    public static ExecutionCancelledException details(String details){
        return new ExecutionCancelledException(UNDEFINED, details);
    }

    public ExecutionCancelledException(){
        this(UNDEFINED, null);
    }

    private ExecutionCancelledException(RunCancellationReason reason, String details){
        this.reason = reason;
        this.additionalDetails = details;
    }

    public RunCancellationReason getReason() {
        return reason;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }
}
