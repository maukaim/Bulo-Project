package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows;

import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.OwnerKeyDto;

public interface OwnerKeyDtoAdapter {
    OwnerKeyDto adapte(OwnerKey ownerKey);
}
