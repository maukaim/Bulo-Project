package com.maukaim.bulo.io.runs.orchestrators.models.flow;

import java.util.Objects;
import java.util.Set;

public class InputDependencyDto {
    private final String inputId;
    private final Set<InputProviderDto> inputProviders;

    public InputDependencyDto(String inputId, Set<InputProviderDto> inputProviders) {
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
        return "IoDependencyDto{" +
                "inputId='" + inputId + '\'' +
                ", inputProviders=" + inputProviders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputDependencyDto that = (InputDependencyDto) o;
        return Objects.equals(inputId, that.inputId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputId);
    }
}
