package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.executors.data.runs.StageRunAncestor;
import com.maukaim.bulo.io.executors.system.in.model.StageRunAncestorDto;

public interface StageRunAncestorAdapter {
    StageRunAncestor adapte(StageRunAncestorDto dto);
}
