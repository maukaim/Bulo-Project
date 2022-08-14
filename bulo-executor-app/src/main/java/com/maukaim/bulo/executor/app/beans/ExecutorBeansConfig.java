package com.maukaim.bulo.executor.app.beans;

import com.maukaim.bulo.executor.core.TechnicalStageRunnerManager;
import com.maukaim.bulo.executor.core.TechnicalStageRunnerManagerImpl;
import com.maukaim.bulo.executor.core.api.TechnicalStageRunner;
import com.maukaim.bulo.runners.embedded.PrintYoloRunner;
import com.maukaim.bulo.runners.embedded.PrintYoloRunner2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class ExecutorBeansConfig {

    @Bean
    public TechnicalStageRunner yoloRunner(){
        return new PrintYoloRunner();
    }


    @Bean
    public TechnicalStageRunnerManager technicalStageRunnerManager(List<TechnicalStageRunner> runners){
        Map<String, TechnicalStageRunner> defaultRunnersMap = runners.stream()
                .collect(Collectors.toMap(
                        runner -> runner.getDefinition().getTechnicalStageDefinitionId(),
                        runner -> runner
                ));
        return new TechnicalStageRunnerManagerImpl(defaultRunnersMap);
    }

}
