package com.maukaim.bulo.commons.io.instructions.models.functional;

import java.util.Set;

public class IoDependencyDto {
    private final String inputId;
    private final Set<InputProviderDto> inputProviders;

    public IoDependencyDto(String inputId, Set<InputProviderDto> inputProviders) {
        this.inputId = inputId;
        this.inputProviders = inputProviders;
    }

    public String getInputId() {
        return inputId;
    }

    public Set<InputProviderDto> getInputProviders() {
        return inputProviders;
    }

    @Override
    public String toString() {
        return "IoDependency{" +
                "inputId='" + inputId + '\'' +
                ", inputProviders=" + inputProviders +
                '}';
    }
}
