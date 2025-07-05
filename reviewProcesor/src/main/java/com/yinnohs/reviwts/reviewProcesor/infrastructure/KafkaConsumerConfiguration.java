package com.yinnohs.reviwts.reviewProcesor.infrastructure;

import com.yinnohs.reviwts.reviewProcesor.application.dto.IncomingReviewEvent;
import org.apache.avro.generic.GenericRecord;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableKafka
public class KafkaConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, GenericRecord> consumerFactory(KafkaProperties properties) {
        // Use KafkaProperties to build the consumer properties from application.yml
        return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties(null));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, GenericRecord> kafkaListenerContainerFactory(
            ConsumerFactory<String, GenericRecord> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, GenericRecord> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}