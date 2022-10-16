package com.maukaim.bulo.commons.models;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AcyclicValidator<T> {
    private final Map<T, Set<T>> ancestorIdsByStageId;

    public AcyclicValidator(Map<T, Set<T>> ancestorIdsByStageId) {
        this.ancestorIdsByStageId = Map.copyOf(ancestorIdsByStageId);
    }

    public void validate() throws IllegalArgumentException {
        Set<T> stageImages = this.ancestorIdsByStageId.keySet();
        for (T stageImage : stageImages) {
            validate(stageImage, new HashSet<>());
        }
    }

    private void validate(T stageImage, Set<T> visited) {
        if (!visited.contains(stageImage)) {
            visited.add(stageImage);
            for (T parentId : this.ancestorIdsByStageId.getOrDefault(stageImage, Set.of())) {
                this.validate(parentId, new HashSet<>(visited));
            }
        } else {
            throw new IllegalArgumentException(String.format(
                    "Cyclic Graph detected! Please check on stage %s",
                    stageImage));
        }
    }
}