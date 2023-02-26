package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.common.data.lifecycle.IoTypeAdapter;
import com.maukaim.bulo.common.data.lifecycle.IoTypeDtoAdapter;
import com.maukaim.bulo.common.data.lifecycle.ParameterTypeAdapter;
import com.maukaim.bulo.common.data.lifecycle.ParameterTypeDtoAdapter;
import com.maukaim.bulo.common.data.lifecycle.impl.IoTypeAdapterImpl;
import com.maukaim.bulo.common.data.lifecycle.impl.IoTypeDtoAdapterImpl;
import com.maukaim.bulo.common.data.lifecycle.impl.ParameterTypeAdapterImpl;
import com.maukaim.bulo.common.data.lifecycle.impl.ParameterTypeDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.ParameterDefinitionAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.ParameterDefinitionDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageDefinitionAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageDefinitionDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageInputDefinitionAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageInputDefinitionDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageOutputDefinitionAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageOutputDefinitionDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.FunctionalSubStageAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.FunctionalSubStageDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.InputProviderAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.InputProviderDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.IoDependencyAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.IoDependencyDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.OutputProviderAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.OutputProviderDtoAdapter;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.FunctionalSubStageAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.FunctionalSubStageDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.InputProviderAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.InputProviderDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.IoDependencyAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.IoDependencyDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.OutputProviderAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.functional.impl.OutputProviderDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.ParameterDefinitionAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.ParameterDefinitionDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.StageDefinitionAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.StageDefinitionDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.StageInputDefinitionAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.StageInputDefinitionDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.StageOutputDefinitionAdapterImpl;
import com.maukaim.bulo.data.lifecycle.definitions.client.impl.StageOutputDefinitionDtoAdapterImpl;
import com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.ParameterAdapter;
import com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.impl.ParameterAdapterImpl;
import com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.impl.StageAdapterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterBeansConfig {

    @Bean
    public StageDefinitionAdapter technicalStageDefinitionAdapter(ParameterDefinitionAdapter parameterDefinitionAdapter,
                                                                  StageInputDefinitionAdapter stageInputDefinitionAdapter,
                                                                  StageOutputDefinitionAdapter stageOutputDefinitionAdapter,
                                                                  FunctionalSubStageAdapter functionalSubStageAdapter,
                                                                  OutputProviderAdapter outputProviderAdapter) {
        return new StageDefinitionAdapterImpl(parameterDefinitionAdapter, stageInputDefinitionAdapter, stageOutputDefinitionAdapter, functionalSubStageAdapter, outputProviderAdapter);
    }

    @Bean
    public OutputProviderAdapter outputProviderAdapter() {
        return new OutputProviderAdapterImpl();
    }

    @Bean
    public FunctionalSubStageAdapter functionalSubStageAdapter(IoDependencyAdapter ioDependencyAdapter) {
        return new FunctionalSubStageAdapterImpl(ioDependencyAdapter);
    }

    @Bean
    public IoDependencyAdapter ioDependencyAdapter(InputProviderAdapter inputProviderAdapter) {
        return new IoDependencyAdapterImpl(inputProviderAdapter);
    }

    @Bean
    public InputProviderAdapter inputProviderAdapter() {
        return new InputProviderAdapterImpl();
    }

    @Bean
    public ParameterDefinitionAdapter parameterDefinitionAdapter(ParameterTypeAdapter parameterTypeAdapter) {
        return new ParameterDefinitionAdapterImpl(parameterTypeAdapter);
    }

    @Bean
    public ParameterTypeAdapter parameterTypeAdapter() {
        return new ParameterTypeAdapterImpl();
    }

    @Bean
    public StageInputDefinitionAdapter stageInputDefinitionAdapter(IoTypeAdapter ioTypeAdapter) {
        return new StageInputDefinitionAdapterImpl(ioTypeAdapter);
    }

    @Bean
    public StageOutputDefinitionAdapter stageOutputDefinitionAdapter(IoTypeAdapter ioTypeAdapter) {
        return new StageOutputDefinitionAdapterImpl(ioTypeAdapter);
    }

    @Bean
    public IoTypeAdapter ioTypeAdapter() {
        return new IoTypeAdapterImpl();
    }

    @Bean
    public StageDefinitionDtoAdapter technicalStageDefinitionDtoAdapter(ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter,
                                                                        StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter,
                                                                        StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter,
                                                                        FunctionalSubStageDtoAdapter functionalSubStageDtoAdapter,
                                                                        OutputProviderDtoAdapter outputProviderDtoAdapter) {
        return new StageDefinitionDtoAdapterImpl(parameterDefinitionDtoAdapter, stageInputDefinitionDtoAdapter, stageOutputDefinitionDtoAdapter, functionalSubStageDtoAdapter, outputProviderDtoAdapter);
    }

    @Bean
    public OutputProviderDtoAdapter outputProviderDtoAdapter() {
        return new OutputProviderDtoAdapterImpl();
    }

    @Bean
    public FunctionalSubStageDtoAdapter functionalSubStageDtoAdapter(IoDependencyDtoAdapter ioDependencyDtoAdapter) {
        return new FunctionalSubStageDtoAdapterImpl(ioDependencyDtoAdapter);
    }

    @Bean
    public IoDependencyDtoAdapter ioDependencyDtoAdapter(InputProviderDtoAdapter inputProviderDtoAdapter) {
        return new IoDependencyDtoAdapterImpl(inputProviderDtoAdapter);
    }

    @Bean
    public InputProviderDtoAdapter inputProviderDtoAdapter() {
        return new InputProviderDtoAdapterImpl();
    }

    @Bean
    public ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter(ParameterTypeDtoAdapter parameterTypeDtoAdapter) {
        return new ParameterDefinitionDtoAdapterImpl(parameterTypeDtoAdapter);
    }

    @Bean
    public ParameterTypeDtoAdapter parameterTypeDtoAdapter() {
        return new ParameterTypeDtoAdapterImpl();
    }

    @Bean
    public StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter(IoTypeDtoAdapter ioTypeDtoAdapter) {
        return new StageInputDefinitionDtoAdapterImpl(ioTypeDtoAdapter);
    }

    @Bean
    public StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter(IoTypeDtoAdapter ioTypeDtoAdapter) {
        return new StageOutputDefinitionDtoAdapterImpl(ioTypeDtoAdapter);
    }

    @Bean
    public IoTypeDtoAdapter ioTypeDtoAdapter() {
        return new IoTypeDtoAdapterImpl();
    }

    @Bean
    public StageAdapter stageAdapter(ParameterAdapter parameterAdapter) {
        return new StageAdapterImpl(parameterAdapter);
    }

    @Bean
    public ParameterAdapter parameterAdapter() {
        return new ParameterAdapterImpl();
    }
}
