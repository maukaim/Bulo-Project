package com.maukaim.bulo.common.data.lifecycle.impl;

import com.maukaim.bulo.api.data.types.NativeType;
import com.maukaim.bulo.api.data.types.parameters.ArrayParameterType;
import com.maukaim.bulo.api.data.types.parameters.BuloParameterType;
import com.maukaim.bulo.api.data.types.parameters.ParameterType;
import com.maukaim.bulo.common.data.lifecycle.ParameterTypeDtoAdapter;
import com.maukaim.bulo.io.data.types.ArrayParameterTypeDto;
import com.maukaim.bulo.io.data.types.BuloParameterTypeDto;
import com.maukaim.bulo.io.data.types.ParameterTypeDto;
import com.maukaim.bulo.io.data.types.natives.impl.BooleanTypeDto;
import com.maukaim.bulo.io.data.types.natives.impl.NumberTypeDto;
import com.maukaim.bulo.io.data.types.natives.impl.StringTypeDto;

import java.util.Map;
import java.util.stream.Collectors;

public class ParameterTypeDtoAdapterImpl implements ParameterTypeDtoAdapter {
    @Override
    public ParameterTypeDto adapte(ParameterType parameterType) {
        return switch (parameterType.getDataTypeCategory()) {
            case NATIVE -> resolveNative((NativeType) parameterType);
            case BULO -> resolveBuloType((BuloParameterType) parameterType);
            case ARRAY -> resolveArrayType((ArrayParameterType) parameterType);
        };
    }

    private ParameterTypeDto resolveArrayType(ArrayParameterType parameterType) {
        return new ArrayParameterTypeDto(
                adapte(parameterType.getContentType()),
                parameterType.isRequired());
    }

    private ParameterTypeDto resolveBuloType(BuloParameterType buloType) {
        return new BuloParameterTypeDto(
                resolveFields(buloType.getFields()),
                buloType.isRequired()
        );
    }

    private ParameterTypeDto resolveNative(NativeType nativeType) {
        return switch (nativeType.getNativeTypeCategory()) {
            case STRING -> new StringTypeDto(nativeType.isRequired());
            case BOOLEAN -> new BooleanTypeDto(nativeType.isRequired());
            case NUMBER -> new NumberTypeDto(nativeType.isRequired());
        };
    }

    private Map<String, ParameterTypeDto> resolveFields(Map<String, ParameterType> fields) {
        return fields == null ? Map.of() : fields.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> adapte(entry.getValue())
                ));
    }
}
