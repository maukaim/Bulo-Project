package com.maukaim.bulo.executors.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.api.data.types.Any;
import com.maukaim.bulo.api.data.types.any.AnyObject;
import com.maukaim.bulo.executors.core.marshalling.jackson.mapper.ObjectMapperProvider;
import com.maukaim.bulo.executors.core.marshalling.BuloRunnerMarshaller;
import com.maukaim.bulo.executors.core.marshalling.jackson.mapper.impl.BuloObjectMapperProvider;
import com.maukaim.bulo.executors.core.marshalling.jackson.resolver.impl.BuloJacksonMarshallingResolver;
import com.maukaim.bulo.executors.core.marshalling.jackson.resolver.JacksonMarshallingResolver;
import com.maukaim.bulo.executors.core.marshalling.jackson.generator.impl.BuloDescriptorMixInGeneratorStrategy;
import com.maukaim.bulo.executors.core.model.Voiture;
import com.maukaim.bulo.runners.api.RunnerMarshaller;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Scanner;

public class JsonDeserializationExperimentalTest {
    private final ObjectMapperProvider mapperProvider = new BuloObjectMapperProvider();
    private final JacksonMarshallingResolver marshallingResolver = new BuloJacksonMarshallingResolver(new BuloDescriptorMixInGeneratorStrategy());

    @Test
    void testMapper() throws JsonProcessingException {
        ObjectMapper buloMapper = buildMapper();
        String json = getResourceFileText("voiture.json");

        Voiture voiture = buloMapper.readValue(json, Voiture.class);
        System.out.println(voiture);
        Voiture voitureEmpty = buloMapper.readValue("{}", Voiture.class);
        System.out.println(voitureEmpty);
    }

    @Test
    void testMarshaller() {
        String json = getResourceFileText("voiture.json");
        RunnerMarshaller buloMarshaller = buildMarshaller();
        Any<?> asGenericType = buloMarshaller.unmarshallAsGenericType(json);
        System.out.println(asGenericType);
        if (asGenericType instanceof AnyObject) {
            System.out.println(asGenericType.getValue());
        }
        System.out.println(buloMarshaller.unmarshall(json, Any.class));
        String jsonArray = getResourceFileText("voitures.json");

        System.out.println(buloMarshaller.unmarshallAsGenericType(jsonArray));
        System.out.println(buloMarshaller.unmarshallAsGenericType("\"test de string\""));
        System.out.println(buloMarshaller.unmarshallAsGenericType("true"));
        System.out.println(buloMarshaller.unmarshallAsGenericType("40"));
        System.out.println(buloMarshaller.marshall("Test de string"));
        System.out.println(buloMarshaller.unmarshall(json, Voiture.class));
        System.out.println(buloMarshaller.unmarshallAsCollection(jsonArray, Voiture.class));
        System.out.println(buloMarshaller.unmarshallAsCollection(json, Voiture.class));
        System.out.println(buloMarshaller.marshall(buloMarshaller.unmarshallAsCollection(json, Voiture.class)));
        String marshall = buloMarshaller.marshall(buloMarshaller.unmarshall(json, Voiture.class));
        System.out.println(marshall);

    }

    private ObjectMapper buildMapper() {
        return mapperProvider.get();
    }

    private RunnerMarshaller buildMarshaller() {
        return new BuloRunnerMarshaller(buildMapper(), marshallingResolver, marshallingResolver);
    }

    private String getResourceFileText(String name) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(name);
        String json;
        try (Scanner scanner = new Scanner(inputStream)) {
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            json = builder.toString();
        }
        return json;
    }

}
