package com.maukaim.bulo.data.lifecycle.data.types.client;

import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.io.data.types.IoTypeDto;

public interface IoTypeDtoAdapter {
    IoTypeDto adapte(IoType ioType);
}
