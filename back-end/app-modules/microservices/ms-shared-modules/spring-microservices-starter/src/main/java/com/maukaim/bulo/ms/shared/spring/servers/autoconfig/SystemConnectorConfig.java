package com.maukaim.bulo.ms.shared.spring.servers.autoconfig;

import com.maukaim.bulo.app.shared.spring.servers.AbstractSpringRestSenderResolver;
import com.maukaim.bulo.app.shared.spring.servers.SystemAwareConfig;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.app.shared.systen.communication.rest.RestSystemEventConnector;
import com.maukaim.bulo.marshalling.Marshaller;
import com.maukaim.bulo.ms.shared.spring.servers.CompoundSystemConnector;
import com.maukaim.bulo.ms.shared.spring.servers.KafkaSenderResolver;
import com.maukaim.bulo.ms.shared.spring.servers.ServiceSpringRestSenderResolver;
import com.maukaim.bulo.ms.shared.spring.servers.autoconfig.conditions.KafkaActivatedCondition;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaConsumer;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaConsumerFactory;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaSystemEventConnector;
import com.maukaim.bulo.ms.shared.system.communication.kafka.topics.KafkaTopicMapProvider;
import com.maukaim.bulo.ms.shared.system.endpoints.SystemEndpointProvider;
import com.maukaim.bulo.shared.server.core.SystemContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Map;

@Configuration
@AutoConfigureBefore(SystemAwareConfig.class)
@AutoConfigureAfter(ApplicationMarshallerConfig.class)
public class SystemConnectorConfig {

    protected RestSystemEventConnector<MicroServiceEventType> getRestSystemConnector(AbstractSpringRestSenderResolver<MicroServiceEventType> restSystemConsumerResolver,
                                                                                     Marshaller marshaller) {
        return new RestSystemEventConnector<>(HttpClient.newHttpClient(), restSystemConsumerResolver, marshaller, false);
    }

    protected AbstractSpringRestSenderResolver<MicroServiceEventType> getRestSystemEventConsumerResolver(SystemContext systemContext) {
        return new ServiceSpringRestSenderResolver(systemContext, SystemEndpointProvider.getSystemEndpointClasses());
    }

    protected KafkaSystemEventConnector getKafkaSystemEventConnector(KafkaSenderResolver kafkaSystemConsumerResolver,
                                                                     Marshaller marshaller) {
        return new KafkaSystemEventConnector(kafkaSystemConsumerResolver, marshaller, false);
    }

    protected KafkaSenderResolver getKafkaSenderResolver(SystemContext systemContext) {
        return new KafkaSenderResolver(systemContext.getKafkaBrokers(),
                KafkaTopicMapProvider.getTopicMap(),
                Map.of());
    }

    protected KafkaConsumerFactory getKafkaConsumerFactory(Marshaller marshaller, SystemContext systemContext) {
        return new KafkaConsumerFactory(marshaller,
                systemContext.getKafkaBrokers().getBrokers(),
                KafkaTopicMapProvider.getTopicMap()
        );
    }

    @Configuration
    @ConditionalOnProperty(prefix = "bulo.communication", value = "mode", havingValue = "compound")
    public class CompoundSystemConnectorConfig {

        @Bean
        @Primary
        @ConditionalOnMissingBean(CompoundSystemConnector.class)
        public SystemConnector<MicroServiceEventType> compoundSystemConnector(KafkaSystemEventConnector mainConnector,
                                                                              RestSystemEventConnector<MicroServiceEventType> secondConnector) {
            return new CompoundSystemConnector(mainConnector, secondConnector);
        }

        @Bean
        @ConditionalOnMissingBean(KafkaSystemEventConnector.class)
        KafkaSystemEventConnector kafkaSystemConnector(KafkaSenderResolver kafkaSystemConsumerResolver,
                                                       Marshaller marshaller) {
            return getKafkaSystemEventConnector(kafkaSystemConsumerResolver, marshaller);
        }

        @Bean
        @ConditionalOnMissingBean(KafkaSenderResolver.class)
        protected KafkaSenderResolver kafkaSenderResolver(SystemContext systemContext) {
            return getKafkaSenderResolver(systemContext);
        }

        @Bean
        @ConditionalOnMissingBean(KafkaConsumerFactory.class)
        protected KafkaConsumerFactory kafkaConsumerFactory(Marshaller marshaller, SystemContext systemContext) {
            return getKafkaConsumerFactory(marshaller, systemContext);
        }

        @Bean
        RestSystemEventConnector<MicroServiceEventType> restSystemConnector(AbstractSpringRestSenderResolver<MicroServiceEventType> restSystemConsumerResolver,
                                                                            Marshaller marshaller) {
            return getRestSystemConnector(restSystemConsumerResolver, marshaller);
        }

        @Bean
        @ConditionalOnMissingBean(AbstractSpringRestSenderResolver.class)
        public AbstractSpringRestSenderResolver<MicroServiceEventType> restSystemEventConsumerResolver(SystemContext systemContext) {
            return getRestSystemEventConsumerResolver(systemContext);
        }
    }

    @Configuration
    @ConditionalOnProperty(prefix = "bulo.communication", value = "mode", havingValue = "kafka")
    public class KafkaSystemConnectorConfig {
        @Bean
        @ConditionalOnMissingBean(KafkaSystemEventConnector.class)
        protected KafkaSystemEventConnector kafkaSystemConnector(KafkaSenderResolver kafkaSystemConsumerResolver,
                                                                 Marshaller marshaller) {
            return getKafkaSystemEventConnector(kafkaSystemConsumerResolver, marshaller);
        }

        @Bean
        @ConditionalOnMissingBean(KafkaSenderResolver.class)
        protected KafkaSenderResolver kafkaSenderResolver(SystemContext systemContext) {
            return getKafkaSenderResolver(systemContext);
        }

        @Bean
        @ConditionalOnMissingBean(KafkaConsumerFactory.class)
        protected KafkaConsumerFactory kafkaConsumerFactory(Marshaller marshaller, SystemContext systemContext) {
            return getKafkaConsumerFactory(marshaller, systemContext);
        }
    }

    @Configuration
    @ConditionalOnProperty(prefix = "bulo.communication", value = "mode", havingValue = "rest", matchIfMissing = true)
    public class RestSystemConnectorConfig {
        @Bean
        protected RestSystemEventConnector<MicroServiceEventType> restSystemConnector(AbstractSpringRestSenderResolver<MicroServiceEventType> restSystemConsumerResolver,
                                                                                      Marshaller marshaller) {
            return getRestSystemConnector(restSystemConsumerResolver, marshaller);
        }

        @Bean
        @ConditionalOnMissingBean(AbstractSpringRestSenderResolver.class)
        public AbstractSpringRestSenderResolver<MicroServiceEventType> restSystemEventConsumerResolver(SystemContext systemContext) {
            return getRestSystemEventConsumerResolver(systemContext);
        }
    }
}


