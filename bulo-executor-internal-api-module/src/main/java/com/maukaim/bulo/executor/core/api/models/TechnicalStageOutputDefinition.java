package com.maukaim.bulo.executor.core.api.models;

import com.maukaim.bulo.commons.models.definitions.OutputDefinition;

public class TechnicalStageOutputDefinition implements OutputDefinition {
    private String name;
    private Boolean canBeMultiple;
    private String typeId;

    public static TechnicalStageOutputDefinition fromJava(String name, boolean canBeMultiple, Class<?> classType){
        return new TechnicalStageOutputDefinition(name, canBeMultiple, classType.getCanonicalName());
    }

    public TechnicalStageOutputDefinition(String name, Boolean canBeMultiple, String typeId) {
        this.name = name;
        this.canBeMultiple = canBeMultiple;
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
    public String toString() {
        return "TechnicalStageOutputDefinition{" +
                "name='" + name + '\'' +
                ", canBeMultiple=" + canBeMultiple +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}
