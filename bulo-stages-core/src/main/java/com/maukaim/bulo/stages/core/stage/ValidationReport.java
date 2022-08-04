package com.maukaim.bulo.stages.core.stage;

import java.util.List;

public class ValidationReport {
    private final boolean validated;
    private final List<String> details;

    public static final ValidationReport DEFAULT_SUCCESS_REPORT = yes();

    public static ValidationReport no(List<String> details){
        return new ValidationReport(false, details);
    }

    public static ValidationReport yes(){
        return new ValidationReport(true, List.of());
    }

    private ValidationReport(boolean validated, List<String> details) {
        this.validated = validated;
        this.details = details;
    }

    public boolean isValidated() {
        return validated;
    }

    public List<String> getDetails() {
        return details;
    }
}
