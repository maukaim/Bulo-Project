package com.maukaim.bulo.io.flows.client.model;

import java.util.Objects;

public class OwnerKeyDto {
    private final String ownerId;
    private final OwnerKeyTypeDto type;

    public OwnerKeyDto(String ownerId, OwnerKeyTypeDto type) {
        this.ownerId = ownerId;
        this.type = type;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public OwnerKeyTypeDto getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerKeyDto ownerKeyDto = (OwnerKeyDto) o;
        return ownerId.equals(ownerKeyDto.ownerId) && type == ownerKeyDto.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, type);
    }

    @Override
    public String toString() {
        return "OwnerKeyDto{" +
                "ownerId='" + ownerId + '\'' +
                ", type=" + type +
                '}';
    }
}
