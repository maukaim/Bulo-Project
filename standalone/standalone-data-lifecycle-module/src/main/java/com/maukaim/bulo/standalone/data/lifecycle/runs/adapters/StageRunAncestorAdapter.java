package com.maukaim.bulo.standalone.data.lifecycle.runs.adapters;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor;

public interface StageRunAncestorAdapter {
    com.maukaim.bulo.executors.data.runs.StageRunAncestor adapte(StageRunAncestor ancestor);
}
