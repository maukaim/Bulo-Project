package com.maukaim.bulo.common.data.lifecycle;

import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.io.data.types.IoTypeDto;

public interface IoTypeDtoAdapter {
    IoTypeDto adapte(IoType ioType);
}
