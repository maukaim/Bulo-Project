package com.maukaim.bulo.flows.io;

import java.util.Objects;

public class OwnerKey {
    private final String id;
    private final OwnerKeyType type;

    public OwnerKey(String id, OwnerKeyType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public OwnerKeyType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerKey ownerKey = (OwnerKey) o;
        return id.equals(ownerKey.id) && type == ownerKey.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
