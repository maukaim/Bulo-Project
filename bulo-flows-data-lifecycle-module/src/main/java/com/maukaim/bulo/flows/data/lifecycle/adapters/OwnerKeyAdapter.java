package com.maukaim.bulo.flows.data.lifecycle.adapters;

import com.maukaim.bulo.flows.data.models.flow.OwnerKey;
import com.maukaim.bulo.flows.io.flow.OwnerKeyDto;

public interface OwnerKeyAdapter {
    OwnerKey adapte(OwnerKeyDto dto);
}
