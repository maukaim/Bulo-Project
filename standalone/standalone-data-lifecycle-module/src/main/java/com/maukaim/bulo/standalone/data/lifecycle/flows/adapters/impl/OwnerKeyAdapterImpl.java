package com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.impl;


import com.maukaim.bulo.flows.data.models.flow.OwnerKey;
import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKeyType;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.OwnerKeyAdapter;

public class OwnerKeyAdapterImpl implements OwnerKeyAdapter {
    public com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey adapte(OwnerKey ownerKey) {
        return ownerKey == null ? null :
                new com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey(
                        ownerKey.getOwnerId(), resolve(ownerKey.getType())
                );
    }

    private OwnerKeyType resolve(com.maukaim.bulo.flows.data.models.flow.OwnerKeyType type) {
        return switch (type) {
            case USER -> OwnerKeyType.USER;
            case TEAM -> OwnerKeyType.TEAM;
        };
    }
}
