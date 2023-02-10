package com.maukaim.bulo.runs.orchestrators.data;

//Why Functional??
public interface FunctionalStageStore {
    String getDefinitionId(String id);
    void put(String functionalStageId, String definitionId);
    String remove(String functionalStageId);
}
