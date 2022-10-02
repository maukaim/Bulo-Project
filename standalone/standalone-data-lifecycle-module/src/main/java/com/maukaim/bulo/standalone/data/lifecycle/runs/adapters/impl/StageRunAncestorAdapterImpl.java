package com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.impl;

import com.maukaim.bulo.executors.data.runs.StageRunAncestor;
import com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.StageRunAncestorAdapter;

public class StageRunAncestorAdapterImpl implements StageRunAncestorAdapter {
    @Override
    public StageRunAncestor adapte(com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor ancestor) {
        return ancestor == null ? null : new StageRunAncestor(
          ancestor.getStageRunId(),
          ancestor.getOutputIds()
        );
    }
}
