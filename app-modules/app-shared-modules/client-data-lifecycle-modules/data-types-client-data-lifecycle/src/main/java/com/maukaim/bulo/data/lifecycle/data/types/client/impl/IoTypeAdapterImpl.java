package com.maukaim.bulo.data.lifecycle.data.types.client.impl;

import com.maukaim.bulo.api.data.types.io.ArrayIoType;
import com.maukaim.bulo.api.data.types.io.BuloIoType;
import com.maukaim.bulo.api.data.types.io.IoType;
import com.maukaim.bulo.api.data.types.natives.BooleanType;
import com.maukaim.bulo.api.data.types.natives.NumberType;
import com.maukaim.bulo.api.data.types.natives.StringType;
import com.maukaim.bulo.data.lifecycle.data.types.client.IoTypeAdapter;
import com.maukaim.bulo.io.data.types.ArrayIoTypeDto;
import com.maukaim.bulo.io.data.types.BuloIoTypeDto;
import com.maukaim.bulo.io.data.types.IoTypeDto;
import com.maukaim.bulo.io.data.types.natives.NativeTypeDto;

import java.util.Map;
import java.util.stream.Collectors;

public class IoTypeAdapterImpl implements IoTypeAdapter {
    @Override
    public IoType adapte(IoTypeDto dto) {
        return switch (dto.getDataTypeCategory()) {
            case NATIVE -> resolveNative((NativeTypeDto) dto);
            case BULO -> resolveBulo((BuloIoTypeDto) dto);
            case ARRAY -> resolveArray((ArrayIoTypeDto) dto);
        };
    }

    private IoType resolveArray(ArrayIoTypeDto dto) {
        return new ArrayIoType(
                adapte(dto.getContentType()),
                dto.isRequired()
        );
    }

    private IoType resolveNative(NativeTypeDto nativeType) {
        return switch (nativeType.getNativeTypeCategory()) {
            case STRING -> new StringType(nativeType.isRequired());
            case BOOLEAN -> new BooleanType(nativeType.isRequired());
            case NUMBER -> new NumberType(nativeType.isRequired());
        };
    }

    private IoType resolveBulo(BuloIoTypeDto dto) {
        return new BuloIoType(
                resolveFields(dto.getFields()),
                dto.isRequired()
        );
    }

    private Map<String, IoType> resolveFields(Map<String, IoTypeDto> fields) {
        return fields == null ? Map.of() : fields.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> adapte(entry.getValue())
                ));
    }
}
