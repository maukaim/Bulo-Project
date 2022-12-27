package com.maukaim.bulo.flows.io.definition;

import com.maukaim.bulo.commons.io.data.types.IoTypeDto;

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
