package com.maukaim.bulo.flows.data.lifecycle;

import com.maukaim.bulo.flows.data.models.flow.OwnerKey;
import com.maukaim.bulo.io.flows.client.model.OwnerKeyDto;

public interface OwnerKeyAdapter {
    OwnerKey adapte(OwnerKeyDto dto);
}
