package com.maukaim.boule.flows.orchestrator.flow.model;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnmodifiableAcyclicExecutionGraph implements ExecutionGraph {
    private Set<String> root;
    private Map<String, Set<String>> ancestorsIdsByStageId;
    private Map<String, Set<String>> childrenIdsByStageId;

    public UnmodifiableAcyclicExecutionGraph(Map<String, Set<String>> stageToAncestors) {
        validateAcyclic(stageToAncestors);
        setUp();
        Predicate<String> isAbsentPredicate = new StageIdIsAbsentPredicate(stageToAncestors.keySet());
        for (Map.Entry<String, Set<String>> entry : stageToAncestors.entrySet()) {
            Set<String> parentIds = entry.getValue();
            String stageId = entry.getKey();

            if (parentIds == null || parentIds.isEmpty()) {
                this.root.add(stageId);
            } else if (parentIds.stream().anyMatch(isAbsentPredicate)) {
                throw new IllegalArgumentException(String.format("Stage %s declare parent(s) that does not exist: %s",
                        stageId,
                        parentIds));
            } else {
                this.ancestorsIdsByStageId.put(stageId, parentIds);
                parentIds.forEach(parentId -> this.addChildren(parentId,stageId));
            }
        }
        if(this.root.isEmpty()){
            throw new IllegalArgumentException("No root elements in the execution graph.");
        }
    }

    private void addChildren(String parentId, String stageId){
        this.childrenIdsByStageId.compute(parentId, (pId, childrenIds) -> {
            if (childrenIds == null) {
                childrenIds = new HashSet<>();
            }
            childrenIds.add(stageId);
            return childrenIds;
        });
    }

    private void validateAcyclic(Map<String, Set<String>> stageToAncestors) {
        new AcyclicValidator(stageToAncestors).validate();
    }

    private void setUp() {
        this.root = new HashSet<>();
        this.ancestorsIdsByStageId = new HashMap<>();
        this.childrenIdsByStageId = new HashMap<>();
    }

    public Set<String> getAllStageIds() {
        return Stream.concat(
                        this.root.stream(),
                        this.ancestorsIdsByStageId.keySet().stream())
                .collect(Collectors.toSet());
    }

    public Set<String> getRootsIds() {
        return this.root;
    }

    public Set<String> getChildren(String stageId) {
        return Set.copyOf(this.childrenIdsByStageId.getOrDefault(stageId, Set.of()));
    }

    public Set<String> getAncestors(String stageId) {
        return Set.copyOf(this.ancestorsIdsByStageId.getOrDefault(stageId, Set.of()));
    }

    private static class StageIdIsAbsentPredicate implements Predicate<String> {
        private final Set<String> allStages;

        public StageIdIsAbsentPredicate(Set<String> allStageIds) {
            this.allStages = allStageIds;
        }

        @Override
        public boolean test(String stageId) {
            return !this.allStages.contains(stageId);
        }
    }

    private static class AcyclicValidator {
        private final Map<String, Set<String>> ancestorIdsByStageId;

        public AcyclicValidator(Map<String, Set<String>> ancestorIdsByStageId) {
            this.ancestorIdsByStageId = Map.copyOf(ancestorIdsByStageId);
        }

        public void validate() throws IllegalArgumentException {
            Set<String> stageIds = this.ancestorIdsByStageId.keySet();
            for (String stageId : stageIds) {
                validate(stageId, new HashSet<>());
            }
        }

        private void validate(String stageId, Set<String> visited) {
            if (!visited.contains(stageId)) {
                visited.add(stageId);
                for (String parentId : this.ancestorIdsByStageId.getOrDefault(stageId, Set.of())) {
                    this.validate(parentId, new HashSet<>(visited));
                }
            } else {
                throw new IllegalArgumentException(String.format(
                        "Cyclic Graph detected! We already visited %s, see -> %s",
                        stageId,
                        visited));
            }
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

