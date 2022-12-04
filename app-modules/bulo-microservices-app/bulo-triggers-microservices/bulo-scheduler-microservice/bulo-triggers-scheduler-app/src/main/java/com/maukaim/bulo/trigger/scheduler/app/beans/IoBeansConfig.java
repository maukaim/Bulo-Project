package com.maukaim.bulo.trigger.scheduler.app.beans;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.ms.connectivity.SystemConnector;
import com.maukaim.bulo.trigger.scheduler.app.io.ScheduleTriggerConsumerImpl;
import com.maukaim.bulo.trigger.scheduler.app.io.TriggerEventPublisherImpl;
import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConsumer;
import com.maukaim.bulo.triggers.io.TriggerEventPublisher;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IoBeansConfig {

    @Bean
    public SystemConnector systemConnector(Jackson2ObjectMapperBuilderCustomizer customizer) {
        return new SystemConnector(getRestTemplate(customizer));
    }

    private RestTemplate getRestTemplate(Jackson2ObjectMapperBuilderCustomizer customizer) {
        Jackson2ObjectMapperBuilder objectMapperBuilder = Jackson2ObjectMapperBuilder.json();
        customizer.customize(objectMapperBuilder);
        ObjectMapper objectMapper = objectMapperBuilder.build();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, converter);
        return restTemplate;
    }

    @Bean
    public TriggerEventPublisher getPublisher(SystemConnector systemConnector) {
        return new TriggerEventPublisherImpl(systemConnector);
    }

    @Bean
    public ScheduleTriggerConsumer scheduleTriggerConsumer(ScheduleTriggerService service) {
        return new ScheduleTriggerConsumerImpl(service);
    }

}
