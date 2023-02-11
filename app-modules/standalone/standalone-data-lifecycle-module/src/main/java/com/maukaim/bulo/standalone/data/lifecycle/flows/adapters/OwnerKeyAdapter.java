package com.maukaim.bulo.standalone.data.lifecycle.flows.adapters;

import com.maukaim.bulo.flows.data.models.flow.OwnerKey;

public interface OwnerKeyAdapter {
    com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey adapte(OwnerKey ownerKey);
}
