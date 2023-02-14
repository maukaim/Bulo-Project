package com.maukaim.bulo.commons.io.instructions.models;

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
                "typeId='" + ioType + '\'' +
                '}';
    }
}
