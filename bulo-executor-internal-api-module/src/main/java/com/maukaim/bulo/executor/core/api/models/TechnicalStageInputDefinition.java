package com.maukaim.bulo.executor.core.api.models;

import com.maukaim.bulo.commons.models.definitions.InputDefinition;

public class TechnicalStageInputDefinition implements InputDefinition {
    private String name;
    private Boolean canBeMultiple;
    private Boolean required;
    private String typeId;

    public static TechnicalStageInputDefinition fromJava(String name, boolean canBeMultiple,boolean required, Class<?> classType){
        return new TechnicalStageInputDefinition(name, canBeMultiple, required, classType.getCanonicalName());
    }

    public TechnicalStageInputDefinition(String name,
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
    public boolean canBeMultiple() {
        return canBeMultiple;
    }

    @Override
    public String getIOTypeId() {
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
