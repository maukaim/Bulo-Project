package com.maukaim.bulo.stages.models.definition;

import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.commons.models.ParameterDefinitionInterface;

import java.util.Objects;

public class ParameterDefinition implements ParameterDefinitionInterface {
    private String name;
    private ParameterType parameterType;
    private String hint;
    private String description;

    public ParameterDefinition(String name, ParameterType parameterType, String hint, String description) {
        this.name = name;
        this.parameterType = parameterType;
        this.hint = hint;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    public String getHint() {
        return hint;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRequired() {
        return getParameterType().isRequired();
    }

    @Override
    public String toString() {
        return "ParameterDefinition{" +
                "name='" + name + '\'' +
                ", parameterType=" + parameterType +
                ", hint='" + hint + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParameterDefinition that = (ParameterDefinition) o;
        return Objects.equals(name, that.name) && Objects.equals(parameterType, that.parameterType) && Objects.equals(hint, that.hint) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parameterType, hint, description);
    }
}
