package com.maukaim.bulo.commons.models;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AcyclicValidator<T> {

    public void validate(Map<T, Set<T>> allAncestorIdMap) throws IllegalArgumentException {
        Set<T> stageImages = allAncestorIdMap.keySet();
        for (T stageImage : stageImages) {
            validate(stageImage, new HashSet<>(), allAncestorIdMap);
        }
    }

    private void validate(T stageImage, Set<T> visited, Map<T, Set<T>> allAncestorIdsMap) {
        if (!visited.contains(stageImage)) {
            visited.add(stageImage);
            for (T parentId : allAncestorIdsMap.getOrDefault(stageImage, Set.of())) {
                this.validate(parentId, new HashSet<>(visited), allAncestorIdsMap);
            }
        } else {
            throw new IllegalArgumentException(String.format(
                    "Cyclic Graph detected! Please check on stage %s",
                    stageImage));
        }
    }
}