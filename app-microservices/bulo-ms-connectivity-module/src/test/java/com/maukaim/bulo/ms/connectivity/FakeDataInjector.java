package com.maukaim.bulo.ms.connectivity;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FakeDataInjector {
    private static final String STAGE_NAME_PROVIDER_ID = "NameProviding";
    private static final String YOLO_PRINT_ID = "PrintYolo";
    private Consumer stageCreateConsumer = new Consumer(Services.STAGES_SERVICE,"api/v1/stages/create");
    private Consumer flowCreateConsumer = new Consumer(Services.FLOWS_SERVICE,"api/v1/flows/createOrUpdate");
    private Consumer triggerFlowRun = new Consumer(Services.ORCHESTRATOR_SERVICE,"api/v1/orchestrator/flowRuns/startFlowRun");

    private String FLOW_ID = "Henriette";
    @Test
    public void testFlowWithStages() throws URISyntaxException, IOException, InterruptedException {
        String nameProvidingStage = send(stageCreateConsumer.toUrl(),
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
        String yoloStage = send(stageCreateConsumer.toUrl(),
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

        String slowYoloStage = send(stageCreateConsumer.toUrl(),
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
                                "value": "Aribaaa ",
                                "valueType": "java.lang.String"
                              }
                            ],
                            "stageType": "TECHNICAL",
                            "definitionId": "SlowPrintYolo"
                          }
                        }
                         """);

        Thread.sleep(500);
        send(flowCreateConsumer.toUrl(), String.format("""
                {
                  "flow": {
                    "allowParallelRun": true,
                    "flowId": "%s",
                    "flowStages": [
                      {
                        "contextStageId": {
                          "stageId": "%s",
                          "marker": 0
                        },
                        "ioDependencies": []
                      },
                      {
                        "contextStageId": {
                          "stageId": "%s",
                          "marker": 0
                        },
                        "ioDependencies": [
                           {
                            "inputId": "Yolo Subject",
                            "inputProviders": [
                              {
                                "contextStageId": {
                                  "stageId": "%s",
                                  "marker": 0
                                },
                                "outputIds": [
                                  "Name"
                                ]
                              }
                            ]
                           }
                         ]
                       },
                       {
                        "contextStageId": {
                          "stageId": "%s",
                          "marker": 0
                        },
                        "ioDependencies": [
                           {
                            "inputId": "Yolo Subject",
                            "inputProviders": [
                              {
                                "contextStageId": {
                                  "stageId": "%s",
                                  "marker": 0
                                },
                                "outputIds": ["Name"]
                              }
                            ]
                           }
                         ]
                        }
                    ],
                    "ownerKeys": [
                      {
                        "ownerId": "BARBSe",
                        "type": "TEAM"
                      }
                    ]
                  },
                  "instant": "2022-09-16T14:22:53.088Z"
                }
                """,
                FLOW_ID,
                nameProvidingStage,
                yoloStage,
                nameProvidingStage,
                slowYoloStage,
                nameProvidingStage
                ));

        Thread.sleep(1000);
        send(triggerFlowRun.toUrl(), String.format("""
                {
                  "flowId": "%s",
                  "contextStageIds":[]
                }
                """, FLOW_ID));

    }

    private String send(String url, String event) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder()
                .uri(new URI(url))
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(event))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(build, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }
}
