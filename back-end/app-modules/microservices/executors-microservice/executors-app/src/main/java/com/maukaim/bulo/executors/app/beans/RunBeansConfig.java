package com.maukaim.bulo.executors.app.beans;

import com.maukaim.bulo.executors.core.*;
import com.maukaim.bulo.executors.core.impl.*;
import com.maukaim.bulo.executors.core.marshalling.MarshallerProvider;
import com.maukaim.bulo.executors.core.marshalling.jackson.mapper.ObjectMapperProvider;
import com.maukaim.bulo.executors.core.marshalling.BuloMarshallerProvider;
import com.maukaim.bulo.executors.core.marshalling.jackson.mapper.impl.BuloObjectMapperProvider;
import com.maukaim.bulo.executors.core.marshalling.jackson.resolver.impl.BuloJacksonMarshallingResolver;
import com.maukaim.bulo.executors.core.marshalling.jackson.resolver.JacksonMarshallingResolver;
import com.maukaim.bulo.executors.core.marshalling.jackson.generator.impl.BuloDescriptorMixInGeneratorStrategy;
import com.maukaim.bulo.executors.data.StageDefinitionStore;
import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.runners.api.StageRunner;
import com.maukaim.bulo.runners.embedded.NameProvider;
import com.maukaim.bulo.runners.embedded.NameProvider2;
import com.maukaim.bulo.runners.embedded.PrintYoloRunner;
import com.maukaim.bulo.runners.embedded.SlowPrintYoloRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Configuration
public class RunBeansConfig {

    @Bean
    public StageRunner yoloRunner() {
        return new PrintYoloRunner();
    }

    @Bean
    public StageRunner nameProviding() {
        return new NameProvider();
    }

    @Bean
    public StageRunner nameProvider2() {
        return new NameProvider2();
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
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        return new RunExecutorImpl(executorService);
    }

    @Bean
    public StageRunManager stageRunnerManager(StageRunnerRegistry runnerRegistry,
                                              RunExecutor runExecutor,
                                              RunOperatorProvider runOperatorProvider) {
        return new StageRunManagerImpl(runnerRegistry, runExecutor, runOperatorProvider);
    }

    @Bean
    public RunOperatorProvider runOperatorProvider(StageRunResultStore stageRunResultStore,
                                                   MarshallerProvider marshallerProvider) {
        return new BuloRunOperatorProvider(stageRunResultStore, marshallerProvider);
    }

    @Bean
    public MarshallerProvider marshallerProvider(ObjectMapperProvider mapperProvider,
                                                 JacksonMarshallingResolver marshallingResolver) {
        return new BuloMarshallerProvider(mapperProvider, marshallingResolver, marshallingResolver);
    }

    @Bean
    public ObjectMapperProvider objectMapperProvider() {
        return new BuloObjectMapperProvider();
    }

    @Bean
    public JacksonMarshallingResolver marshallingResolver(BuloDescriptorMixInGeneratorStrategy mixInGeneratorStrategy) {
        return new BuloJacksonMarshallingResolver(mixInGeneratorStrategy);
    }

    @Bean
    public BuloDescriptorMixInGeneratorStrategy buloDescriptorMixInGeneratorStrategy() {
        return new BuloDescriptorMixInGeneratorStrategy();
    }

    @Bean
    public StageRunProcessor stageRunEventProcessor(StageRunManager stageRunManager,
                                                    StageStore stageStore,
                                                    StageRunResultStore stageRunResultStore,
                                                    StageDefinitionStore stageDefinitionStore) {
        return new StageRunProcessorImpl(stageRunManager, stageStore, stageRunResultStore, stageDefinitionStore);
    }
}
