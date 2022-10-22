package com.maukaim.bulo.definitions.data.definition.functional;

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
                ", inputProviders=" + inputProviders +
                '}';
    }
}
