package com.maukaim.bulo.flows.io.flow;

import java.util.Objects;

public class OwnerKeyDto {
    private final String id;
    private final OwnerKeyTypeDto type;

    public OwnerKeyDto(String id, OwnerKeyTypeDto type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public OwnerKeyTypeDto getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerKeyDto ownerKeyDto = (OwnerKeyDto) o;
        return id.equals(ownerKeyDto.id) && type == ownerKeyDto.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
