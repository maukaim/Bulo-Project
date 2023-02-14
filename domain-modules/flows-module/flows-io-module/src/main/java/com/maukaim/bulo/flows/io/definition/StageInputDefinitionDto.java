package com.maukaim.bulo.flows.io.definition;

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
