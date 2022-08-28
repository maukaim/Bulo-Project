package com.maukaim.bulo.flows.data.models.flow;

import java.util.Objects;
import java.util.Set;

public class IoDependency {
    private final String inputId;
    private final Set<InputProvider> inputProviders;

    public IoDependency(String inputId, Set<InputProvider> inputProviders) {
        this.inputId = inputId;
        this.inputProviders = inputProviders;
    }


    public String getInputId() {
        return inputId;
    }

    public Set<InputProvider> getInputProviders() {
        return inputProviders;
    }

    @Override
    public String toString() {
        return "IoDependency{" +
                "inputId='" + inputId + '\'' +
                ", inputProvider=" + inputProviders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IoDependency that = (IoDependency) o;
        return inputId.equals(that.inputId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputId);
    }
}
