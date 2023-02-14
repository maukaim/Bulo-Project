package com.maukaim.bulo.commons.io.instructions.models;

import com.maukaim.bulo.io.data.types.IoTypeDto;

public class StageInputDefinitionDto {
    private IoTypeDto ioType;

    public StageInputDefinitionDto(IoTypeDto ioType) {
        this.ioType = ioType;
    }

    public IoTypeDto getIoType() {
        return ioType;
    }

    @Override
    public String toString() {
        return "StageInputDefinitionDto{" +
                "ioType=" + ioType +
                '}';
    }
}
