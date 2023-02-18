package com.maukaim.bulo.flows.data.lifecycle;

import com.maukaim.bulo.flows.data.models.flow.OwnerKey;
import com.maukaim.bulo.io.flows.flow.OwnerKeyDto;

public interface OwnerKeyDtoAdapter {
    OwnerKeyDto adapte(OwnerKey ownerKey);
}
