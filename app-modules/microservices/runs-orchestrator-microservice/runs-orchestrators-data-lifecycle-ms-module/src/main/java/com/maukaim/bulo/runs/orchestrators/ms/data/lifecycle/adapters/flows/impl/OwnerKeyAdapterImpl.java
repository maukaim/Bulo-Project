package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey;
import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKeyType;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.OwnerKeyAdapter;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.OwnerKeyDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.OwnerKeyTypeDto;

public class OwnerKeyAdapterImpl implements OwnerKeyAdapter {
    @Override
    public OwnerKey adapte(OwnerKeyDto dto) {
        return new OwnerKey(
                dto.getOwnerId(),
                resolve(dto.getType())
        );
    }

    private OwnerKeyType resolve(OwnerKeyTypeDto type) {
        return type == null ? null : switch (type){
            case USER -> OwnerKeyType.USER;
            case TEAM -> OwnerKeyType.TEAM;
        };
    }
}
