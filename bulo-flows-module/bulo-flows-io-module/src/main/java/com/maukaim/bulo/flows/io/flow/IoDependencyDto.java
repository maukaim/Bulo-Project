package com.maukaim.bulo.flows.io.flow;

import java.util.Objects;
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
        return "IoDependencyDto{" +
                "inputId='" + inputId + '\'' +
                ", inputProviders=" + inputProviders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IoDependencyDto that = (IoDependencyDto) o;
        return Objects.equals(inputId, that.inputId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputId);
    }
}
