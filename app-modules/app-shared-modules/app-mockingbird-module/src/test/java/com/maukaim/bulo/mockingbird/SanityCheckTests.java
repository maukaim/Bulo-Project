package com.maukaim.bulo.mockingbird;

import com.maukaim.bulo.app.shared.servers.model.ApplicationEnvironment;
import com.maukaim.bulo.app.shared.system.communication.api.ApplicationMode;
import com.maukaim.bulo.commons.io.data.types.natives.impl.StringTypeDto;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.mockingbird.builders.StageDtoBuilder;
import com.maukaim.bulo.mockingbird.connect.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.maukaim.bulo.mockingbird.builders.FunctionalDefinitionBuilder.*;

public class SanityCheckTests {
    private User user;

    private final ApplicationMode applicationMode = ApplicationMode.microservices;
    private final ApplicationEnvironment applicationEnv = ApplicationEnvironment.dev;

    @BeforeEach
    void init() {
        user = switch (applicationEnv) {
            case dev -> new User(TestManager.getDevUIApp(applicationMode));
            case uat -> new User(TestManager.getUatUIApp(applicationMode));
            case prod -> throw new UnsupportedOperationException("Don't use mockingbird with PROD environement");
        };
    }

    @Test
    void sanityCheckCustomIOType() {
        String stageId = user.createStage(
                StageDtoBuilder.aTechnicalStage()
                        .withDefinitionId("NameProviding2")
                        .withParameter("Name",
                                "[[{\"motor\": {\"vitesseMax\": 4},\"brand\": \"Toyota\",\"isUsable\": true}]]",
                                "Testing if a the ParameterType declared conclude to parse this one.")
                        .build()
        );

        System.out.println(stageId);
    }

    @Test
    void SanityCheckMicroservices() {
        //1 - Create 2 Technical Stages.
        List<String> stageIds = user.createMultipleStages(
                StageDtoBuilder.aTechnicalStage()
                        .withDefinitionId("NameProviding")
                        .withParameter("Name", "Fitzgerald", "Surnom de Chloé")
                        .build(),

                StageDtoBuilder.aTechnicalStage()
                        .withDefinitionId("PrintYolo")
                        .withParameter("Uppercase", "false", "Set if result should be uppercase or not.")
                        .withParameter("Greetings", "Hola, buenos dias ", "Espagnol style...")
                        .build()
        );
        System.out.println(stageIds);

        ContextStageId nameProviderContextId = ContextStageId.of(stageIds.get(0), 0);
        ContextStageId printerContextId1 = ContextStageId.of(stageIds.get(1), 0);
        ContextStageId printerContextId2 = ContextStageId.of(stageIds.get(1), 1);

        //2 - Create a Functional Stage Definition
        String functionalStageDefinitionId = user.createFunctionalDefinition(
                aDefinition()
                        .withInput("Yolo Subject", StringTypeDto.required())
                        .withOutput("Yolo Result", StringTypeDto.required())
                        .withParameters(/*empty params*/)
                        .withFsStages(
                                fsStage(nameProviderContextId, Set.of()),
                                fsStage(printerContextId2, Set.of(ioDependency("Yolo Subject", Set.of()))),
                                fsStage(printerContextId1, Set.of(ioDependency("Yolo Subject", Set.of(inputProvider(nameProviderContextId, "Name")))))
                        )
                        .withOutputProviders(outputProvider(printerContextId2, "Yolo Result"))
                        .build());

        System.out.println(functionalStageDefinitionId);

        //3 - Create a Functional Stage
        String functionalStageId = user.createStage(
                StageDtoBuilder.aFunctionalStage()
                        .withDefinitionId(functionalStageDefinitionId)
                        .withEmptyParameter()
                        .build()
        );

        //4 - Create a Flow

        //5 - Trigger a FlowRun
    }
}