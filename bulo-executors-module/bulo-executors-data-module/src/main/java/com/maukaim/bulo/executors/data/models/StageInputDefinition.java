package com.maukaim.bulo.executors.data.models;

import com.maukaim.bulo.commons.models.definitions.InputDefinition;

public class StageInputDefinition implements InputDefinition {
    private String name;
    private Boolean canBeMultiple;
    private Boolean required;
    private String typeId;

    public static StageInputDefinition fromJava(String name, boolean canBeMultiple, boolean required, Class<?> classType){
        return new StageInputDefinition(name, canBeMultiple, required, classType.getCanonicalName());
    }

    public StageInputDefinition(String name,
                                Boolean acceptMultiple,
                                Boolean required,
                                String typeId) {
        this.name = name;
        this.canBeMultiple = acceptMultiple;
        this.required = required;
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isCanBeMultiple() {
        return canBeMultiple;
    }

    @Override
    public String getTypeId() {
        return typeId;
    }

    @Override
    public Boolean isRequired() {
        return required;
    }

    @Override
    public String toString() {
        return "StageInputDefinition{" +
                "name='" + name + '\'' +
                ", canBeMultiple=" + canBeMultiple +
                ", required=" + required +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}
