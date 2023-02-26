package com.maukaim.bulo.data.lifecycle.flows.client.impl;

import com.maukaim.bulo.data.lifecycle.flows.client.OwnerKeyDtoAdapter;
import com.maukaim.bulo.flows.data.models.flow.OwnerKey;
import com.maukaim.bulo.flows.data.models.flow.OwnerKeyType;
import com.maukaim.bulo.io.flows.client.model.OwnerKeyDto;
import com.maukaim.bulo.io.flows.client.model.OwnerKeyTypeDto;

public class OwnerKeyDtoAdapterImpl implements OwnerKeyDtoAdapter {

    @Override
    public OwnerKeyDto adapte(OwnerKey ownerKey) {
        return new OwnerKeyDto(ownerKey.getOwnerId(), adapteOwnerKeyType(ownerKey.getType()));
    }

    private OwnerKeyTypeDto adapteOwnerKeyType(OwnerKeyType type) {
        return switch (type) {
            case USER -> OwnerKeyTypeDto.USER;
            case TEAM -> OwnerKeyTypeDto.TEAM;
        };
    }
}
