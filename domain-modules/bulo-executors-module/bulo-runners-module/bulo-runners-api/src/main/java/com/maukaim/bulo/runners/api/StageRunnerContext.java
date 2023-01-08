package com.maukaim.bulo.runners.api;

import com.maukaim.bulo.api.data.types.Any;

import java.util.Collection;
import java.util.Map;

public class StageRunnerContext {
    private final Map<String, String> inputs;
    private final Map<String, String> parameters;
    private final Marshaller marshaller;

    public StageRunnerContext(Map<String, String> inputs, Map<String, String> parameters, Marshaller marshaller) {
        this.inputs = inputs;
        this.parameters = parameters;
        this.marshaller = marshaller;
    }

    public String getRawParameter(String parameterName) {
        return this.parameters.get(parameterName);
    }

    public String getRawInput(String inputName) {
        return this.inputs.get(inputName);
    }

    public Any<?> getAsGenericParameter(String parameterName) {
        String rawParameter = getRawParameter(parameterName);
        if (rawParameter != null) {
            return this.marshaller.unmarshallAsGenericType(rawParameter);
        }
        return null;
    }

    public Any<?> getAsGenericInput(String parameterName) {
        String rawInput = getRawInput(parameterName);
        if (rawInput != null) {
            return this.marshaller.unmarshallAsGenericType(rawInput);
        }
        return null;
    }

    public <T> T getParameter(String parameterName, Class<T> clazz) {
        String rawParameter = getRawParameter(parameterName);
        if (rawParameter != null) {
            return this.marshaller.unmarshall(rawParameter, clazz);
        }
        return null;
    }

    public <T> T getInput(String inputName, Class<T> clazz) {
        String rawInput = getRawInput(inputName);
        if (rawInput != null) {
            return this.marshaller.unmarshall(rawInput, clazz);
        }
        return null;
    }

    public <T> Collection<T> getListParameter(String parameterName, Class<T> clazz) {
        String rawParameter = getRawParameter(parameterName);
        if (rawParameter != null) {
            return this.marshaller.unmarshallAsCollection(rawParameter, clazz);
        }
        return null;
    }

    public <T> Collection<T> getListInput(String inputName, Class<T> clazz) {
        String rawInput = getRawInput(inputName);
        if (rawInput != null) {
            return this.marshaller.unmarshallAsCollection(rawInput, clazz);
        }
        return null;
    }
}
