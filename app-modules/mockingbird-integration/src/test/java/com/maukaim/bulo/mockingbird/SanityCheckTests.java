package com.maukaim.bulo.mockingbird;

import com.maukaim.bulo.shared.server.core.model.ApplicationEnvironment;
import com.maukaim.bulo.app.shared.system.communication.api.ApplicationMode;
import com.maukaim.bulo.io.data.types.natives.impl.StringTypeDto;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.io.flows.system.flow.OwnerKeyTypeDto;
import com.maukaim.bulo.mockingbird.builders.FlowDtoBuilder;
import com.maukaim.bulo.mockingbird.builders.FunctionalDefinitionBuilder;
import com.maukaim.bulo.mockingbird.builders.StageDtoBuilder;
import com.maukaim.bulo.mockingbird.connect.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.maukaim.bulo.mockingbird.builders.FlowDtoBuilder.inputProvider;
import static com.maukaim.bulo.mockingbird.builders.FlowDtoBuilder.ioDependency;
import static com.maukaim.bulo.mockingbird.builders.FunctionalDefinitionBuilder.*;

public class SanityCheckTests {
    private User user;

    private final ApplicationMode applicationMode = ApplicationMode.microservices;
    private final ApplicationEnvironment applicationEnv = ApplicationEnvironment.dev;

    @BeforeEach
    void init() {
        user = switch (applicationEnv) {
            case dev -> new User(TestUtil.getDevApp(applicationMode));
            case uat -> new User(TestUtil.getUatApp(applicationMode));
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
                                fsStage(printerContextId2, Set.of(FunctionalDefinitionBuilder.ioDependency("Yolo Subject", Set.of()))),
                                fsStage(printerContextId1, Set.of(FunctionalDefinitionBuilder.ioDependency("Yolo Subject", Set.of(FunctionalDefinitionBuilder.inputProvider(nameProviderContextId, "Name")))))
                        )
                        .withOutputProviders(outputProvider(printerContextId2, "Yolo Result"))
                        .build());

        //3 - Create a Functional Stage
        String functionalStageId = user.createStage(
                StageDtoBuilder.aFunctionalStage()
                        .withDefinitionId(functionalStageDefinitionId)
                        .withEmptyParameter()
                        .build()
        );

        //4 - Create a Flow
        ContextStageId flowNameProviderContextId = ContextStageId.of(stageIds.get(0), 0);
        ContextStageId flowPrinterContextId1 = ContextStageId.of(stageIds.get(1), 0);
        ContextStageId flowPrinterContextId2 = ContextStageId.of(stageIds.get(1), 1);
        ContextStageId functionalNameProviderContextId = ContextStageId.of(functionalStageId, 4);

        String flowId = user.createFlow(FlowDtoBuilder.aFlow()
                .withFlowId("Babar")
                .withOwnerKey(user.getUserName(), OwnerKeyTypeDto.USER)
                .withFlowStage(flowNameProviderContextId)
                .withFlowStage(flowPrinterContextId1,
                        ioDependency("Yolo Subject",
                                inputProvider(flowNameProviderContextId, "Name")
                        ))
                .withFlowStage(flowPrinterContextId2,
                        ioDependency("Yolo Subject",
                                inputProvider(functionalNameProviderContextId, "Yolo Result")
                        ))
                .withFlowStage(functionalNameProviderContextId,
                        ioDependency("Yolo Subject",
                                inputProvider(flowNameProviderContextId, "Name")
                        ))
                .withAllowParallelRun(true)
                .build());

        //5 - Trigger a FlowRun
        user.triggerFlowRun(TriggerId.of(flowId, Set.of()));
    }
}
