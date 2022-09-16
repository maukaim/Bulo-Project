package com.maukaim.bulo.ms.connectivity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.stages.io.events.CreateStageEvent;
import com.maukaim.bulo.stages.io.models.stages.ParameterDto;
import com.maukaim.bulo.stages.io.models.stages.TechnicalStageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakeDataInjector {
    private static final String STAGE_NAME_PROVIDER_ID = "NameProviding";
    private static final String YOLO_PRINT_ID = "PrintYolo";
    private Consumer stageCreateConsumer = new Consumer(Services.STAGES_SERVICE,"api/v1/stages/create");

    @Test
    public void injectStages() throws URISyntaxException, IOException, InterruptedException {
        CreateStageEvent stageNameProvider = new CreateStageEvent(
                new TechnicalStageDto(List.of(
                        new ParameterDto("Fitzgerald", "Name", "java.lang.String", "Un nom bien a porter.")
                ), STAGE_NAME_PROVIDER_ID),
                Instant.now()
        );
        CreateStageEvent yoloPrinter = new CreateStageEvent(
                new TechnicalStageDto(List.of(
                        new ParameterDto("false", "Uppercase", "java.lang.Boolean", "Met en Uppercase ou pas."),
                        new ParameterDto("Buenos Dias! ", "Greetings", "java.lang.String", "C'est du Finlandais biensur...")
                ), YOLO_PRINT_ID),
                Instant.now()
        );

        send(stageCreateConsumer.toUrl(),
                """
                     {
                        "instant": "2022-09-16T09:17:39.651Z",
                        "stageDto": {
                        "parameters": [
                         {
                           "additionalDetails": "string",
                           "name": "Name",
                           "value": "Fitzgerald",
                           "valueType": "java.lang.String"
                         }
                        ],
                        "stageType": "TECHNICAL",
                        "definitionId": "NameProviding"
                        }
                     }
                        """);
        send(stageCreateConsumer.toUrl(),
                """
                       {
                         "instant": "2022-09-16T09:17:39.651Z",
                         "stageDto": {
                           "parameters": [
                             {
                               "additionalDetails": "string",
                               "name": "Uppercase",
                               "value": "true",
                               "valueType": "java.lang.Boolean"
                             },
                             {
                               "additionalDetails": "string",
                               "name": "Greetings",
                               "value": "Buenos Dias! ",
                               "valueType": "java.lang.String"
                             }
                           ],
                           "stageType": "TECHNICAL",
                           "definitionId": "PrintYolo"
                         }
                       }
                        """);
    }

    private void send(String url, String event) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder()
                .uri(new URI(url))
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(event))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        httpClient.send(build, HttpResponse.BodyHandlers.ofString());
    }
}
