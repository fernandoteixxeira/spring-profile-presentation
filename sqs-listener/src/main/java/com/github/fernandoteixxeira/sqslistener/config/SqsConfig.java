package com.github.fernandoteixxeira.sqslistener.config;

import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.sqs.SqsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Profile("!keyAuth & !local")
public class SqsConfig {

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder().build();
    }
}