package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.io.executors.system.dtos.StageRunResultDto;

public interface StageRunResultAdapter {
    StageRunResult adapte(StageRunResultDto dto);
}
