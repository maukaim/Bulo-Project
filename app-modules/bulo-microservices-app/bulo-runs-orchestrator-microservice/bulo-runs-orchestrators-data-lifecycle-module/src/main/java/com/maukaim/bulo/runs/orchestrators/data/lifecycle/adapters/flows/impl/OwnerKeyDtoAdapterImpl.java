package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey;
import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKeyType;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.OwnerKeyDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.OwnerKeyDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.OwnerKeyTypeDto;

public class OwnerKeyDtoAdapterImpl implements OwnerKeyDtoAdapter {
    @Override
    public OwnerKeyDto adapte(OwnerKey ownerKey) {
        return new OwnerKeyDto(
                ownerKey.getOwnerId(),
                resolve(ownerKey.getType())
        );
    }

    private OwnerKeyTypeDto resolve(OwnerKeyType type) {
        return type == null ? null : switch (type){
            case USER -> OwnerKeyTypeDto.USER;
            case TEAM -> OwnerKeyTypeDto.TEAM;
        };
    }
}
