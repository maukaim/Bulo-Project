package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows;

import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.OwnerKeyDto;

public interface OwnerKeyAdapter {
    OwnerKey adapte(OwnerKeyDto dto);
}
