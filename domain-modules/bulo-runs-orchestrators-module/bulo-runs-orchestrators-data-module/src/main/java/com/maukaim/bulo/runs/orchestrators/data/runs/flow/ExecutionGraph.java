package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutionGraph {
    private Set<ContextualizedStageId> root;
    private Map<ContextualizedStageId, Set<ContextualizedStageDependency>> dependenciesByFlowStageId;
    private Map<ContextualizedStageId, Set<ContextualizedStageId>> childrenIdsByStageId;

    public ExecutionGraph(Map<ContextualizedStageId, Set<ContextualizedStageDependency>> dependencyMap) {
        setUp(dependencyMap);
    }

    private void setUp(Map<ContextualizedStageId, Set<ContextualizedStageDependency>> dependencyMap) {
        this.root = new HashSet<>();
        this.dependenciesByFlowStageId = new HashMap<>();
        this.childrenIdsByStageId = new HashMap<>();

        for (Map.Entry<ContextualizedStageId, Set<ContextualizedStageDependency>> dependencyEntry : dependencyMap.entrySet()) {
            ContextualizedStageId contextualizedStageId = dependencyEntry.getKey();
            Set<ContextualizedStageDependency> stageRunDependencies = dependencyEntry.getValue();
            if (stageRunDependencies == null || stageRunDependencies.isEmpty()) {
                this.root.add(contextualizedStageId);
            } else {
                this.dependenciesByFlowStageId.put(contextualizedStageId, stageRunDependencies);
                stageRunDependencies.stream()
                        .map(stageRunDependency -> stageRunDependency.getAncestors())
                        .filter(Objects::nonNull)
                        .flatMap(Collection::stream)
                        .forEach(ancestor -> {
                            ContextualizedStageId ancestorId = ancestor.getFlowStageId();
                            this.childrenIdsByStageId.compute(ancestorId, (key, val) -> {
                                if (val == null) {
                                    val = new HashSet<>();
                                }
                                val.add(contextualizedStageId);
                                return val;
                            });
                        });
            }
        }
    }

    public Set<ContextualizedStageDependency> getFlowStageDependencies(ContextualizedStageId contextualizedStageId) {
        return this.dependenciesByFlowStageId.getOrDefault(contextualizedStageId, Set.of());
    }

    public Map<ContextualizedStageId, Set<ContextualizedStageDependency>> toDependencyMap() {
        return Stream.concat(
                        this.getRootsIds().stream()
                                .map(f -> Map.entry(f, Set.<ContextualizedStageDependency>of())),
                        this.dependenciesByFlowStageId.entrySet().stream()
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Set<ContextualizedStageId> getRootsIds() {
        return Set.copyOf(this.root);
    }

    public Set<ContextualizedStageId> getChildren(ContextualizedStageId stageId) {
        return Set.copyOf(this.childrenIdsByStageId.getOrDefault(stageId, Set.of()));
    }

    public Set<ContextualizedStageId> getAncestors(ContextualizedStageId stageId) {
        if (this.dependenciesByFlowStageId.containsKey(stageId)) {
            return this.dependenciesByFlowStageId.get(stageId).stream()
                    .map(inputDependency -> inputDependency.getAncestors())
                    .flatMap(Collection::stream)
                    .map(inputProvider -> inputProvider.getFlowStageId())
                    .collect(Collectors.toSet());
        } else {
            return Set.of();
        }
    }
}