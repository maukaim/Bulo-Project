package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.io.flows.client.model.OwnerKeyDto;
import com.maukaim.bulo.io.flows.client.model.OwnerKeyTypeDto;
import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey;
import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKeyType;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.OwnerKeyDtoAdapter;

public class OwnerKeyDtoAdapterImpl implements OwnerKeyDtoAdapter {
    @Override
    public OwnerKeyDto adapte(OwnerKey ownerKey) {
        return new OwnerKeyDto(
                ownerKey.getOwnerId(),
                resolve(ownerKey.getType())
        );
    }

    private OwnerKeyTypeDto resolve(OwnerKeyType type) {
        return type == null ? null : switch (type) {
            case USER -> OwnerKeyTypeDto.USER;
            case TEAM -> OwnerKeyTypeDto.TEAM;
        };
    }
}
