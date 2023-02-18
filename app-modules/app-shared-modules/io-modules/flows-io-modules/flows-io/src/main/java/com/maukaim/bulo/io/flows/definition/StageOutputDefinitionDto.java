package com.maukaim.bulo.io.flows.definition;

import com.maukaim.bulo.io.data.types.IoTypeDto;

public class StageOutputDefinitionDto {
    private IoTypeDto ioType;

    public StageOutputDefinitionDto(IoTypeDto ioType) {
        this.ioType = ioType;
    }

    public IoTypeDto getIoType() {
        return ioType;
    }

    @Override
    public String toString() {
        return "StageOutputDefinitionDto{" +
                "ioType=" + ioType +
                '}';
    }
}
