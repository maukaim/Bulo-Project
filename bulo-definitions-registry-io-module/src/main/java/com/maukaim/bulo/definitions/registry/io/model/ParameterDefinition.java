package com.maukaim.bulo.definitions.registry.io.model;

public class ParameterDefinition {
    private String name;
    private Class type;
    private String hint;
    private String description;
    private Boolean required;

    public ParameterDefinition(String name, Class type, String hint, String description, boolean required) {
        this.name = name;
        this.type = type;
        this.hint = hint;
        this.description = description;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
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
        return "Parameters{" +
                "key='" + name + '\'' +
                ", type=" + type +
                ", hint='" + hint + '\'' +
                ", description='" + description + '\'' +
                ", required=" + required +
                '}';
    }

}
