package com.maukaim.bulo.stages.models.definition;

import com.maukaim.bulo.commons.models.ParameterDefinitionInterface;

import java.util.Objects;

public class ParameterDefinition implements ParameterDefinitionInterface {
    private String name;
    private String valueType;
    private String hint;
    private String description;
    private Boolean required;

    public ParameterDefinition(String name, String valueType, String hint, String description, boolean required) {
        this.name = name;
        this.valueType = valueType;
        this.hint = hint;
        this.description = description;
        this.required = required;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getValueType() {
        return valueType;
    }

    public String getHint() {
        return hint;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRequired() {
        return required;
    }


    @Override
    public String toString() {
        return "ParameterDefinition{" +
                "name='" + name + '\'' +
                ", valueType='" + valueType + '\'' +
                ", hint='" + hint + '\'' +
                ", description='" + description + '\'' +
                ", required=" + required +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParameterDefinition that = (ParameterDefinition) o;
        return Objects.equals(name, that.name) && Objects.equals(valueType, that.valueType) && Objects.equals(hint, that.hint) && Objects.equals(description, that.description) && Objects.equals(required, that.required);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, valueType, hint, description, required);
    }
}
