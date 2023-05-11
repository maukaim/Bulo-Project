package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows;

import com.maukaim.bulo.io.flows.client.model.OwnerKeyDto;
import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey;

public interface OwnerKeyAdapter {
    OwnerKey adapte(OwnerKeyDto dto);
}
