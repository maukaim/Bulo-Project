package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutionGraph {
    private Set<FlowStageId> root;
    private Map<FlowStageId, Set<FlowStageDependency>> dependenciesByFlowStageId;
    private Map<FlowStageId, Set<FlowStageId>> childrenIdsByStageId;

    public ExecutionGraph(Map<FlowStageId, Set<FlowStageDependency>> dependencyMap) {
        setUp(dependencyMap);
    }

    private void setUp(Map<FlowStageId, Set<FlowStageDependency>> dependencyMap) {
        this.root = new HashSet<>();
        this.dependenciesByFlowStageId = new HashMap<>();
        this.childrenIdsByStageId = new HashMap<>();

        for (Map.Entry<FlowStageId, Set<FlowStageDependency>> dependencyEntry : dependencyMap.entrySet()) {
            FlowStageId flowStageId = dependencyEntry.getKey();
            Set<FlowStageDependency> stageRunDependencies = dependencyEntry.getValue();
            if (stageRunDependencies == null || stageRunDependencies.isEmpty()) {
                this.root.add(flowStageId);
            } else {
                this.dependenciesByFlowStageId.put(flowStageId, stageRunDependencies);
                stageRunDependencies.stream()
                        .map(stageRunDependency -> stageRunDependency.getAncestors())
                        .filter(Objects::nonNull)
                        .flatMap(Collection::stream)
                        .forEach(ancestor -> {
                            FlowStageId ancestorId = ancestor.getFlowStageId();
                            this.childrenIdsByStageId.compute(ancestorId, (key, val) -> {
                                if (val == null) {
                                    val = new HashSet<>();
                                }
                                val.add(flowStageId);
                                return val;
                            });
                        });
            }
        }
    }

    public Set<FlowStageDependency> getFlowStageDependencies(FlowStageId flowStageId){
        return this.dependenciesByFlowStageId.getOrDefault(flowStageId, Set.of());
    }

    public Map<FlowStageId, Set<FlowStageDependency>> toDependencyMap() {
        return Stream.concat(
                        this.getRootsIds().stream()
                                .map(f -> Map.entry(f, Set.<FlowStageDependency>of())),
                        this.dependenciesByFlowStageId.entrySet().stream()
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Set<FlowStageId> getRootsIds() {
        return Set.copyOf(this.root);
    }

    public Set<FlowStageId> getChildren(FlowStageId stageId) {
        return Set.copyOf(this.childrenIdsByStageId.getOrDefault(stageId, Set.of()));
    }

    public Set<FlowStageId> getAncestors(FlowStageId stageId) {
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