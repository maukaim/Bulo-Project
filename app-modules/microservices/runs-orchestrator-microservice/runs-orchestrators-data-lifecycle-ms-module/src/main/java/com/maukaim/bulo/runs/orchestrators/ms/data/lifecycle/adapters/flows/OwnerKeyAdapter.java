package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows;

import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.OwnerKeyDto;

public interface OwnerKeyAdapter {
    OwnerKey adapte(OwnerKeyDto dto);
}
