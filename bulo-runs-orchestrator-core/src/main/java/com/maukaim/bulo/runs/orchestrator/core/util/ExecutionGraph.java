package com.maukaim.bulo.runs.orchestrator.core.util;

import java.util.Set;

public interface ExecutionGraph<KEY> {
    Set<KEY> getAllStageIds();

    Set<KEY> getRootsIds();

    Set<KEY> getChildren(KEY stageId);

    Set<KEY> getAncestors(KEY stageId);
}
