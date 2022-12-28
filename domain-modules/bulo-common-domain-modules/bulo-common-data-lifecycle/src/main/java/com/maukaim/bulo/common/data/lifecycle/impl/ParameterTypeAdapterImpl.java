package com.maukaim.bulo.common.data.lifecycle.impl;

import com.maukaim.bulo.api.data.types.natives.BooleanType;
import com.maukaim.bulo.api.data.types.natives.NumberType;
import com.maukaim.bulo.api.data.types.natives.StringType;
import com.maukaim.bulo.api.data.types.parameters.ArrayParameterType;
import com.maukaim.bulo.api.data.types.parameters.BuloParameterType;
import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.common.data.lifecycle.ParameterTypeAdapter;
import com.maukaim.bulo.commons.io.data.types.ArrayParameterTypeDto;
import com.maukaim.bulo.commons.io.data.types.BuloParameterTypeDto;
import com.maukaim.bulo.commons.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.commons.io.data.types.natives.NativeTypeDto;
import com.maukaim.bulo.commons.io.data.types.natives.impl.BooleanTypeDto;
import com.maukaim.bulo.commons.io.data.types.natives.impl.NumberTypeDto;
import com.maukaim.bulo.commons.io.data.types.natives.impl.StringTypeDto;

import java.util.Map;
import java.util.stream.Collectors;

public class ParameterTypeAdapterImpl implements ParameterTypeAdapter {
    @Override
    public ParameterType adapte(ParameterTypeDto dto) {
        return switch (dto.getDataTypeCategory()) {
            case NATIVE -> resolveNative((NativeTypeDto) dto);
            case BULO -> resolveBuloType((BuloParameterTypeDto) dto);
            case ARRAY -> resolveArrayType((ArrayParameterTypeDto) dto);
        };
    }

    private ParameterType resolveArrayType(ArrayParameterTypeDto dto) {
        return new ArrayParameterType(
                adapte(dto.getContentType()),
                dto.isRequired()
        );
    }

    private ParameterType resolveBuloType(BuloParameterTypeDto dto) {
        return new BuloParameterType(
                resolveFields(dto.getFields()),
                dto.isRequired()
        );
    }

    private ParameterType resolveNative(NativeTypeDto dto) {
        return switch (dto.getNativeTypeCategory()) {
            case STRING -> resolve((StringTypeDto) dto);
            case BOOLEAN -> resolve((BooleanTypeDto) dto);
            case NUMBER -> resolve((NumberTypeDto) dto);
        };
    }

    private ParameterType resolve(NumberTypeDto dto) {
        return new NumberType(dto.isRequired());
    }

    private ParameterType resolve(BooleanTypeDto dto) {
        return new BooleanType(dto.isRequired());
    }

    private ParameterType resolve(StringTypeDto dto) {
        return new StringType(dto.isRequired());
    }

    private Map<String, ParameterType> resolveFields(Map<String, ParameterTypeDto> fields) {
        return fields == null ? Map.of() : fields.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> adapte(entry.getValue())
                ));
    }
}
