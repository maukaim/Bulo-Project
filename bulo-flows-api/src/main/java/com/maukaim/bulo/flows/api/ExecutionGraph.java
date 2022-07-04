package com.maukaim.bulo.flows.api;

import java.util.Set;

public interface ExecutionGraph<KEY> {
    Set<KEY> getAllStageIds();

    Set<KEY> getRootsIds();

    Set<KEY> getChildren(KEY stageId);

    Set<KEY> getAncestors(KEY stageId);
}
