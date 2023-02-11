package com.maukaim.bulo.commons.models.definitions;

import com.maukaim.bulo.api.data.types.io.IoType;

public interface IoDefinition {
    boolean canBeMultiple();
    IoType getType();
}
