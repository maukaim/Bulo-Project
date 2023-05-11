package com.maukaim.bulo.io.definitions.client.dtos;

import com.maukaim.bulo.io.data.types.IoTypeDto;

public class StageOutputDefinitionDto {
    private final IoTypeDto ioType;

    public StageOutputDefinitionDto(IoTypeDto ioType) {
        this.ioType = ioType;
    }

    public IoTypeDto getIoType() {
        return ioType;
    }

    @Override
    public String toString() {
        return "StageOutputDefinitionDto{" +
                "typeId='" + ioType + '\'' +
                '}';
    }
}
