package com.maukaim.bulo.executors.data.models;

import com.maukaim.bulo.commons.models.definitions.OutputDefinition;

public class StageOutputDefinition implements OutputDefinition {
    private String name;
    private Boolean canBeMultiple;
    private String typeId;

    public static StageOutputDefinition fromJava(String name, boolean canBeMultiple, Class<?> classType){
        return new StageOutputDefinition(name, canBeMultiple, classType.getCanonicalName());
    }

    public StageOutputDefinition(String name, Boolean canBeMultiple, String typeId) {
        this.name = name;
        this.canBeMultiple = canBeMultiple;
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
    public String toString() {
        return "TechnicalStageOutputDefinition{" +
                "name='" + name + '\'' +
                ", canBeMultiple=" + canBeMultiple +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}