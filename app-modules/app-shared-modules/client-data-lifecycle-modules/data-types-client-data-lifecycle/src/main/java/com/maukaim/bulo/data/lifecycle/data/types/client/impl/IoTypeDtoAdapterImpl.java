package com.maukaim.bulo.data.lifecycle.data.types.client.impl;

import com.maukaim.bulo.api.data.types.NativeType;
import com.maukaim.bulo.api.data.types.io.ArrayIoType;
import com.maukaim.bulo.api.data.types.io.BuloIoType;
import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.data.lifecycle.data.types.client.IoTypeDtoAdapter;
import com.maukaim.bulo.io.data.types.ArrayIoTypeDto;
import com.maukaim.bulo.io.data.types.BuloIoTypeDto;
import com.maukaim.bulo.io.data.types.IoTypeDto;
import com.maukaim.bulo.io.data.types.natives.impl.BooleanTypeDto;
import com.maukaim.bulo.io.data.types.natives.impl.NumberTypeDto;
import com.maukaim.bulo.io.data.types.natives.impl.StringTypeDto;

import java.util.Map;
import java.util.stream.Collectors;

public class IoTypeDtoAdapterImpl implements IoTypeDtoAdapter {
    @Override
    public IoTypeDto adapte(IoType ioType) {
        return switch (ioType.getDataTypeCategory()) {
            case NATIVE -> resolveNative((NativeType) ioType);
            case BULO -> resolveBulo((BuloIoType) ioType);
            case ARRAY -> resolveArray((ArrayIoType) ioType);
        };
    }

    private IoTypeDto resolveArray(ArrayIoType ioType) {
        return new ArrayIoTypeDto(
                adapte(ioType.getContentType()),
                ioType.isRequired()
        );
    }

    private IoTypeDto resolveNative(NativeType nativeType) {
        return switch (nativeType.getNativeTypeCategory()) {
            case STRING -> new StringTypeDto(nativeType.isRequired());
            case BOOLEAN -> new BooleanTypeDto(nativeType.isRequired());
            case NUMBER -> new NumberTypeDto(nativeType.isRequired());
        };
    }

    private IoTypeDto resolveBulo(BuloIoType ioType) {
        return new BuloIoTypeDto(
                resolveFields(ioType.getFields()),
                ioType.isRequired()
        );
    }

    private Map<String, IoTypeDto> resolveFields(Map<String, IoType> fields) {
        return fields == null ? Map.of() : fields.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> adapte(entry.getValue())
                ));
    }
}
