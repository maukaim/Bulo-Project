package com.maukaim.bulo.mockingbird;

import com.maukaim.bulo.app.shared.servers.model.ApplicationEnvironment;
import com.maukaim.bulo.app.shared.system.communication.api.ApplicationMode;
import com.maukaim.bulo.mockingbird.builders.StageDtoBuilder;
import com.maukaim.bulo.mockingbird.connect.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NormalFlowCreationTest {
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
    void sanityCheckCustomIOType(){
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

        //2 - Create a Functional Stage Definition

        //3 - Create a Functional Stage

        //4 - Create a Flow

        //5 - Trigger a FlowRun
    }
}
