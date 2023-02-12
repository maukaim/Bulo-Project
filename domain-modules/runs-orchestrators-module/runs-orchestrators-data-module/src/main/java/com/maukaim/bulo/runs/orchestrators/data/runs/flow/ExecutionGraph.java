package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutionGraph {
    private Set<ContextStageId> root;
    private Map<ContextStageId, Set<ContextualizedStageDependency>> dependenciesByFlowStageId;
    private Map<ContextStageId, Set<ContextStageId>> childrenIdsByStageId;

    public ExecutionGraph(Map<ContextStageId, Set<ContextualizedStageDependency>> dependencyMap) {
        setUp(dependencyMap);
    }

    private void setUp(Map<ContextStageId, Set<ContextualizedStageDependency>> dependencyMap) {
        this.root = new HashSet<>();
        this.dependenciesByFlowStageId = new HashMap<>();
        this.childrenIdsByStageId = new HashMap<>();

        for (Map.Entry<ContextStageId, Set<ContextualizedStageDependency>> dependencyEntry : dependencyMap.entrySet()) {
            ContextStageId contextStageId = dependencyEntry.getKey();
            Set<ContextualizedStageDependency> stageRunDependencies = dependencyEntry.getValue();
            if (stageRunDependencies == null || stageRunDependencies.isEmpty()) {
                this.root.add(contextStageId);
            } else {
                if (hasOnlyDependenciesWithNoProviders(stageRunDependencies)) {
                    this.root.add(contextStageId);
                }
                this.dependenciesByFlowStageId.put(contextStageId, stageRunDependencies);
                stageRunDependencies.stream()
                        .map(stageRunDependency -> stageRunDependency.getAncestors())
                        .filter(Objects::nonNull)
                        .flatMap(Collection::stream)
                        .forEach(ancestor -> {
                            ContextStageId ancestorId = ancestor.getContextStageId();
                            this.childrenIdsByStageId.compute(ancestorId, (key, val) -> {
                                if (val == null) {
                                    val = new HashSet<>();
                                }
                                val.add(contextStageId);
                                return val;
                            });
                        });
            }
        }
    }

    private boolean hasOnlyDependenciesWithNoProviders(Set<ContextualizedStageDependency> stageRunDependencies) {
        return !stageRunDependencies.isEmpty() && stageRunDependencies.stream().allMatch(runDependency -> {
            Set<ContextStageAncestor> ancestors = runDependency.getAncestors();
            return ancestors == null || ancestors.isEmpty();
        });

    }

    public Set<ContextualizedStageDependency> getFlowStageDependencies(ContextStageId contextStageId) {
        return this.dependenciesByFlowStageId.getOrDefault(contextStageId, Set.of());
    }

    public Map<ContextStageId, Set<ContextualizedStageDependency>> toDependencyMap() {
        return Stream.concat(
                        this.getRootsIds().stream()
                                .map(f -> Map.entry(f, Set.<ContextualizedStageDependency>of())),
                        this.dependenciesByFlowStageId.entrySet().stream()
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Set<ContextStageId> getRootsIds() {
        return Set.copyOf(this.root);
    }

    public Set<ContextStageId> getChildren(ContextStageId stageId) {
        return Set.copyOf(this.childrenIdsByStageId.getOrDefault(stageId, Set.of()));
    }

    public Set<ContextStageId> getAncestors(ContextStageId stageId) {
        if (this.dependenciesByFlowStageId.containsKey(stageId)) {
            return this.dependenciesByFlowStageId.get(stageId).stream()
                    .map(inputDependency -> inputDependency.getAncestors())
                    .flatMap(Collection::stream)
                    .map(inputProvider -> inputProvider.getContextStageId())
                    .collect(Collectors.toSet());
        } else {
            return Set.of();
        }
    }

    public Set<ContextStageId> getLeavesIds() {
        return dependenciesByFlowStageId.keySet().stream()
                .filter(contextualizedStageId -> !this.childrenIdsByStageId.containsKey(contextualizedStageId))
                .collect(Collectors.toSet());
    }
}