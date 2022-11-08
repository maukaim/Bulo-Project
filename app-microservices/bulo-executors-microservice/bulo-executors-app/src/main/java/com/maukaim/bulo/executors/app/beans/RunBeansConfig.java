package com.maukaim.bulo.executors.app.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.executors.core.*;
import com.maukaim.bulo.executors.core.impl.*;
import com.maukaim.bulo.executors.data.StageDefinitionStore;
import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.StageRunner;
import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.runners.core.SimpleJsonMarshaller;
import com.maukaim.bulo.runners.embedded.NameProvider;
import com.maukaim.bulo.runners.embedded.PrintYoloRunner;
import com.maukaim.bulo.runners.embedded.SlowPrintYoloRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class RunBeansConfig {

    @Bean
    public StageRunner yoloRunner() {
        return new PrintYoloRunner(new SimpleJsonMarshaller(new ObjectMapper()));
    }

    @Bean
    public StageRunner nameProviding() {
        return new NameProvider();
    }

    @Bean
    public StageRunner slowYoloRunner() {
        return new SlowPrintYoloRunner();
    }

    @Bean
    public StageRunnerRegistry runnerRegistry(List<StageRunner> runners) {
        Map<String, StageRunner> defaultRunnersMap = runners.stream()
                .collect(Collectors.toMap(
                        runner -> runner.getDefinition().getDefinitionId(),
                        runner -> runner
                ));
        return new StageRunnerRegistryImpl(defaultRunnersMap);
    }

    @Bean
    public RunExecutor runExecutor() {
        return new RunExecutorImpl(4);
    }

    @Bean
    public StageRunManager stageRunnerManager(StageRunnerRegistry runnerRegistry,
                                                       RunExecutor runExecutor,
                                                       RunOperatorProvider runOperatorProvider) {
        return new StageRunManagerImpl(runnerRegistry, runExecutor, runOperatorProvider);
    }

    @Bean
    public RunOperatorProvider runOperatorProvider(StageRunResultStore stageRunResultStore){
        return new RunOperatorProviderImpl(stageRunResultStore);
    }

    @Bean
    public StageRunEventProcessor stageRunEventProcessor(StageRunManager stageRunManager,
                                                         StageStore stageStore,
                                                         StageRunResultStore stageRunResultStore,
                                                         StageDefinitionStore stageDefinitionStore) {
        return new StageRunEventProcessorImpl(stageRunManager, stageStore, stageRunResultStore, stageDefinitionStore);
    }
}