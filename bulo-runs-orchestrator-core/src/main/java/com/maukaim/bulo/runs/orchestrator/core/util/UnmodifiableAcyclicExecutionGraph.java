package com.maukaim.bulo.runs.orchestrator.core.util;

import com.maukaim.bulo.commons.core.AcyclicValidator;
import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnmodifiableAcyclicExecutionGraph implements ExecutionGraph<FlowStageId> {
    private Set<FlowStageId> root;
    private Map<FlowStageId, Set<FlowStageId>> ancestorsIdsByStageId;
    private Map<FlowStageId, Set<FlowStageId>> childrenIdsByStageId;

    public UnmodifiableAcyclicExecutionGraph(Map<FlowStageId, Set<FlowStageId>> stageToAncestors) {
        validateAcyclic(stageToAncestors);
        setUp();
        Predicate<FlowStageId> isAbsentPredicate = StageIdIsAbsentPredicate.withContext(stageToAncestors.keySet());
        for (Map.Entry<FlowStageId, Set<FlowStageId>> entry : stageToAncestors.entrySet()) {
            Set<FlowStageId> parentIds = entry.getValue();
            FlowStageId stageId = entry.getKey();

            if (parentIds == null || parentIds.isEmpty()) {
                this.root.add(stageId);
            } else if (parentIds.stream().anyMatch(isAbsentPredicate)) {
                throw new IllegalArgumentException(String.format("Stage %s declare parent(s) that does not exist: %s",
                        stageId,
                        parentIds));
            } else {
                this.ancestorsIdsByStageId.put(stageId, parentIds);
                parentIds.forEach(parentId -> this.addChildren(parentId, stageId));
            }
        }
        if (this.root.isEmpty()) {
            throw new IllegalArgumentException("No root elements in the execution graph.");
        }
    }

    private void addChildren(FlowStageId parentId, FlowStageId stageId) {
        this.childrenIdsByStageId.compute(parentId, (pId, childrenIds) -> {
            if (childrenIds == null) {
                childrenIds = new HashSet<>();
            }
            childrenIds.add(stageId);
            return childrenIds;
        });
    }

    private void validateAcyclic(Map<FlowStageId, Set<FlowStageId>> stageToAncestors) {
        new AcyclicValidator<>(stageToAncestors).validate();
    }

    private void setUp() {
        this.root = new HashSet<>();
        this.ancestorsIdsByStageId = new HashMap<>();
        this.childrenIdsByStageId = new HashMap<>();
    }

    public Set<FlowStageId> getAllStageIds() {
        return Stream.concat(
                        this.root.stream(),
                        this.ancestorsIdsByStageId.keySet().stream())
                .collect(Collectors.toSet());
    }

    public Set<FlowStageId> getRootsIds() {
        return this.root;
    }

    public Set<FlowStageId> getChildren(FlowStageId stageId) {
        return Set.copyOf(this.childrenIdsByStageId.getOrDefault(stageId, Set.of()));
    }

    public Set<FlowStageId> getAncestors(FlowStageId stageId) {
        return Set.copyOf(this.ancestorsIdsByStageId.getOrDefault(stageId, Set.of()));
    }

    private static class StageIdIsAbsentPredicate implements Predicate<FlowStageId> {
        private final Set<FlowStageId> allStages;

        public static StageIdIsAbsentPredicate withContext(Set<FlowStageId> allStageIds) {
            return new StageIdIsAbsentPredicate(allStageIds);
        }

        private StageIdIsAbsentPredicate(Set<FlowStageId> allStageIds) {
            this.allStages = allStageIds;
        }

        @Override
        public boolean test(FlowStageId stageId) {
            return !this.allStages.contains(stageId);
        }
    }

    @Override
    public String toString() {
        return "UnmodifiableAcyclicExecutionGraph{" +
                "root=" + root +
                ", ancestorsIdsByStageId=" + ancestorsIdsByStageId +
                ", childrenIdsByStageId=" + childrenIdsByStageId +
                '}';
    }
}

