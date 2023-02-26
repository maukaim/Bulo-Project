package com.maukaim.bulo.data.lifecycle.flows.client.impl;

import com.maukaim.bulo.data.lifecycle.flows.client.OwnerKeyAdapter;
import com.maukaim.bulo.flows.data.models.flow.OwnerKey;
import com.maukaim.bulo.flows.data.models.flow.OwnerKeyType;
import com.maukaim.bulo.io.flows.client.model.OwnerKeyDto;
import com.maukaim.bulo.io.flows.client.model.OwnerKeyTypeDto;

public class OwnerKeyAdapterImpl implements OwnerKeyAdapter {

    @Override
    public OwnerKey adapte(OwnerKeyDto dto) {
        return new OwnerKey(dto.getOwnerId(), adapteOwnerKeyType(dto.getType()));
    }

    private OwnerKeyType adapteOwnerKeyType(OwnerKeyTypeDto type) {
        return switch (type) {
            case USER -> OwnerKeyType.USER;
            case TEAM -> OwnerKeyType.TEAM;
        };
    }
}
