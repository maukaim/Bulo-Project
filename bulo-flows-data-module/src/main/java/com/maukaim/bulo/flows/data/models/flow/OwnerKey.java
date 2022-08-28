package com.maukaim.bulo.flows.data.models.flow;

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
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
