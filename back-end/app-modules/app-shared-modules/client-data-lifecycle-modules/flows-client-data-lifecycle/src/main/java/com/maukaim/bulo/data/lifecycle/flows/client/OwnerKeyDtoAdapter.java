package com.maukaim.bulo.data.lifecycle.flows.client;

import com.maukaim.bulo.flows.data.models.flow.OwnerKey;
import com.maukaim.bulo.io.flows.client.model.OwnerKeyDto;

public interface OwnerKeyDtoAdapter {
    OwnerKeyDto adapte(OwnerKey ownerKey);
}
