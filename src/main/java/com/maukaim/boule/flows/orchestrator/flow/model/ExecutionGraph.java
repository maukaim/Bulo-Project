package com.maukaim.boule.flows.orchestrator.flow.model;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ExecutionGraph {
    Set<String> getAllStageIds();

    Set<String> getRootsIds();

    Set<String> getChildren(String stageId);

    Set<String> getAncestors(String stageId);
}
