package com.maukaim.bulo.common.data.lifecycle;

import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.commons.io.data.types.IoTypeDto;

public interface IoTypeAdapter {
    IoType adapte(IoTypeDto dto);
}
