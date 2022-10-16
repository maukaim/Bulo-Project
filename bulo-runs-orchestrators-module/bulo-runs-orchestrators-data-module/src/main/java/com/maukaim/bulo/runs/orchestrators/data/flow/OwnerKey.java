package com.maukaim.bulo.runs.orchestrators.data.flow;

import java.util.Objects;

public class OwnerKey {
    private final String ownerId;
    private final OwnerKeyType type;

    public OwnerKey(String ownerId, OwnerKeyType type) {
        this.ownerId = ownerId;
        this.type = type;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public OwnerKeyType getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, type);
    }

    @Override
    public String toString() {
        return "OwnerKey{" +
                "ownerId='" + ownerId + '\'' +
                ", type=" + type +
                '}';
    }
}
